package chen.questiong.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 连接数据库
 * Created by chen on 2016/11/4.
 */
public class DataBase {

    private SQLiteDatabase db;
    public int DBParam;
    public int DBParam1;


    //获取数据库的数据
    public List<Question> getQuestionA() {
        List<Question> list = new ArrayList<>();
        Cursor cursor;

        if (DBParam == 1) {
            db = SQLiteDatabase.openDatabase("/data/data/chen.questiong/databases/nuita.db",
                    null, SQLiteDatabase.OPEN_READWRITE);
            if (DBParam1 == 1) {
                cursor = db.rawQuery("select * from nuita", null);
            }else if (DBParam1 == 2) {
                cursor = db.rawQuery("select * from nuitb", null);
            }else if (DBParam1 == 3) {
                cursor = db.rawQuery("select * from unitc", null);
            }else if (DBParam1 == 4) {
                cursor = db.rawQuery("select * from nuitd", null);
            }else if (DBParam1 == 5) {
                cursor = db.rawQuery("select * from unite", null);
            }else if (DBParam1 == 6) {
                cursor = db.rawQuery("select * from unitf", null);
            }else if (DBParam1 == 7) {
                cursor = db.rawQuery("select * from unitg", null);
            } else if (DBParam1 == 8) {
                cursor = db.rawQuery("select * from unith", null);
            } else if (DBParam1 == 9){
                cursor = db.rawQuery("select * from dan", null);
            } else {
                cursor = null;
            }

        }else {
            cursor = null;
        }

        //执行sql语句
        //Cursor cursor = db.rawQuery("select * from nuita", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            //遍历
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Question question = new Question();
                //ID
                question.ID = cursor.getInt(cursor.getColumnIndex("Field1"));
                //问题
                question.question = cursor.getString(cursor.getColumnIndex("Field2"));
                //四个选择
                question.answerA = cursor.getString(cursor.getColumnIndex("Field3"));
                question.answerB = cursor.getString(cursor.getColumnIndex("Field4"));
                question.answerC = cursor.getString(cursor.getColumnIndex("Field5"));
                question.answerD = cursor.getString(cursor.getColumnIndex("Field6"));
                //答案
                question.answer = cursor.getInt(cursor.getColumnIndex("Field7"));
                //解析
                question.explaination = cursor.getString(cursor.getColumnIndex("Field8"));
                //设置为没有选择任何选项
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;

    }

}
