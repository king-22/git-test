package com.example.jyotirmay.utilities.todo;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jyotirmay.utilities.todo.db.TaskTable;
import com.example.jyotirmay.utilities.todo.model.Task;
import com.example.jyotirmay.utilities.R;

import java.util.ArrayList;
import java.util.Calendar;


public class TODOActivity extends AppCompatActivity {

public static final String TAG1 = "hey";
    TimePicker timePicker;
    Button button_date;
    TextView Tv_date, Tv_time;
    DatePickerDialog datePickerDialog;
    int day, month, year;
    public static final String TAG ="CURSOR";
    ListView LV_TASK;
    EditText ET_TASK;
    Button BTN_TASK ,BTN_DeleteAll;
    ArrayList<tasks.task> task_list;
    String TASK;
    String TASKDATE;
    int TASKDONE,flag=0;
    AppCompatCheckBox CB;

    SQLiteDatabase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Tv_date = (TextView) findViewById(R.id.tv_date);
        LV_TASK = (ListView) findViewById(R.id.lv_task);
        ET_TASK = (EditText) findViewById(R.id.et_task);
        BTN_TASK = (Button) findViewById(R.id.btn_addTask);
        button_date = (Button)findViewById(R.id.btn_date);
        BTN_DeleteAll = (Button) findViewById(R.id.btn_delete);
        myDb = myDbOpener.openWritableDatabase(TODOActivity.this);

        task_list = tasks.getTasks();


        updateDb();



        showDatePickerDialog();

        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });





        BTN_TASK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 if (Tv_date.getText().toString().isEmpty() ||ET_TASK.getText().toString().isEmpty() ) {
                    Toast.makeText(TODOActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                   }
                else{
                     addTask();
                 }
                //  MyDbOpener myDbOpener = new MyDbOpener(this);
                //  SQLiteDatabase myDb = myDbOpener.getWritableDatabase();

                ET_TASK.setText(null);
                Tv_date.setText(null);
            }
        });





    }
    public void addTask(){
        TASKDATE =  Tv_date.getText().toString();
        TASK =  ET_TASK.getText().toString();
        TASKDONE = 0;

        Task stu  = new Task(
                TASKDATE,
                TASK,
                TASKDONE

        );
        ContentValues value = new ContentValues();
        value.put(TaskTable.Columns.DATE,stu.getDate());
        value.put(TaskTable.Columns.NAME,stu.getName());
        value.put(TaskTable.Columns.DONE,stu.getDone());

        long id =  myDb.insert(TaskTable.TABLE_NAME , null , value);
        Log.d(TAG, "onClick: Data inserted in Database " + "id = " + id );
        if(id==-1){
            Toast.makeText(TODOActivity.this, "Invalid Entry" , Toast.LENGTH_SHORT).show();
        }
        else{
            updateDb();
            Toast.makeText(TODOActivity.this, "New Task Added", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateDb(){


        String[] projection =  {
                TaskTable.Columns.DATE,
                TaskTable.Columns.NAME,
                TaskTable.Columns.DONE
        };
        String sortOrder =
                TaskTable.Columns.DATE ;

        final Cursor c = myDb.query(
                TaskTable.TABLE_NAME,
                projection,
                null,null,null,null,sortOrder
        );

        //if count is zero, do nothing (means if database is empty)
        if(c.getCount()==0){
            return;
        }

        Log.d(TAG, "onCreate: Cursor count=" + c.getCount() );
        c.moveToFirst();
        Log.d(TAG, "updateDb:new  " + c.getPosition());



        final TaskListAdapter taskListAdapter = new TaskListAdapter(task_list);
        LV_TASK.setAdapter(taskListAdapter);


        task_list.clear();
        taskListAdapter.notifyDataSetChanged();
        do {

            String taskdate = c.getString(c.getColumnIndex(TaskTable.Columns.DATE));
            String taskname = c.getString(c.getColumnIndex(TaskTable.Columns.NAME));
            Log.d(TAG, "updateDb: " + c.getPosition());
            Log.d(TAG, "onCreate: task = " + taskdate + " " + taskname);
            task_list.add(new tasks.task(taskname,taskdate));
            taskListAdapter.notifyDataSetChanged();

            //  TV.setText(TV.getText().toString()+"\n"+stuId + " " + stuName);
        }while (c.moveToNext());

        BTN_DeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_list.clear();
                taskListAdapter.notifyDataSetChanged();
                myDb.delete(TaskTable.TABLE_NAME,null,null);

                Toast.makeText(TODOActivity.this, "All Tasks Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        LV_TASK.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                tasks.task thistask;
                thistask = task_list.get(position);
                myDb.delete(TaskTable.TABLE_NAME,TaskTable.Columns.NAME + " = ? " , new String[]{thistask.TaskName});
                Toast.makeText(TODOActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemLongClick: " +  c.getCount());
                if(c.getCount()==1){
                    task_list.remove(position);
                    taskListAdapter.notifyDataSetChanged();
                    flag=1;
                }
                else{
                    updateDb();
                }




                return false;
            }
        });
        LV_TASK.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AppCompatCheckBox cb = (AppCompatCheckBox) view.findViewById(R.id.cb_done);
                cb.toggle();

//                TextView n = (TextView) view.findViewById(R.id.tv_taskname);
//                n.setPaintFlags(n.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//                TextView d = (TextView) view.findViewById(R.id.tv_taskdate);
//                d.setPaintFlags(d.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });




    }
    private class TaskListAdapter extends BaseAdapter {



        class CourseViewHolder {
            TextView TASKNameView;
            TextView TASKDATEVIEW;
        }

        private ArrayList<tasks.task> mTasks;

        public TaskListAdapter(ArrayList<tasks.task> mTasks) {
            this.mTasks = mTasks;
        }

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public tasks.task getItem(int position) {
            return mTasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


       @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater li = getLayoutInflater();
            CourseViewHolder courseViewHolder;

          //  Log.d(TAG, "getView: called");

            if (convertView == null) {
               // Log.d(TAG, "getView: convertView == null");
                convertView = li.inflate(R.layout.list_item_task_todo, null);
                courseViewHolder = new CourseViewHolder();
                courseViewHolder.TASKDATEVIEW =
                        (TextView) convertView.findViewById(R.id.tv_taskdate);
                courseViewHolder.TASKNameView =
                        (TextView) convertView.findViewById(R.id.tv_taskname);

                convertView.setTag(courseViewHolder);
            } else {
              //  Log.d(TAG, "getView: convertView != null");
                courseViewHolder = (CourseViewHolder) convertView.getTag();
            }

            tasks.task thisTask = getItem(position);

            courseViewHolder.TASKNameView.setText(thisTask.TaskName);
            courseViewHolder.TASKDATEVIEW.setText(thisTask.TaskDate);
            return convertView;
        }
    }

    public void showDatePickerDialog() {




        Calendar calendar = Calendar.getInstance();
       datePickerDialog =new DatePickerDialog(TODOActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker , int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                c.set(i, i1, i2);
                month = i1 + 1;
                Tv_date.setText(i + "-" + month + "-" + i2);
            }
        }

                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));






    }
}







