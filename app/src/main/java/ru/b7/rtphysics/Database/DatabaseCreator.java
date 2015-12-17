package ru.b7.rtphysics.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;


/**
 * Created by Nikita on 01.11.2015.
 */
public class DatabaseCreator extends SQLiteOpenHelper {

    private static final String DB_NAME = "finalbasa";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase myDB;
    private static String DB_PATH = "/data/data/ru.b7.rtphysics/databases/";
    Context myContext;

    /**
     * Конструктор
     * Принимает и сохраняет ссылку на переданный контекст для доступа к ресурсам приложения
     * @param context
     */
    public DatabaseCreator(Context context) {
        super(context, DB_NAME, null, 1);

        this.myContext = context;
        //Почистить от этого говна!!!!!!!!

        createDataBase();
    }

    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     * */
    public void createDataBase() {

        boolean dbExist = checkDataBase();

        //если базы нет
        if (!dbExist) {
            //вызывая этот метод создаем пустую базу, позже она будет перезаписана

            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }
    }

    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase(){
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    /**
     * Копирует базу из папки assets заместо созданной локальной БД
     * Выполняется путем копирования потока байтов.
     * */
    private void copyDataBase() throws IOException {

         //Открываем локальную БД как входящий поток
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);

        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0){

            myOutput.write(buffer, 0, length);

        }
        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException{

        //открываем БД
        String myPath = DB_PATH + DB_NAME;
        myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDB != null)
            myDB.close();
        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    /* try {
         createDataBase();
     } catch(IOException exc){
         System.out.println("Error with copying");
     }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Здесь можно добавить вспомогательные методы для доступа и получения данных из БД
    // вы можете возвращать курсоры через "return myDataBase.query(....)", это облегчит их использование
    // в создании адаптеров для ваших view
}
