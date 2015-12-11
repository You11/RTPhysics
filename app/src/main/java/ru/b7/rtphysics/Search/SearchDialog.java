package ru.b7.rtphysics.Search;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.R;

/**
 * Created by Домашний on 29.11.2015.
 */

public class SearchDialog extends Thread implements DialogInterface.OnClickListener{

    private Context currentContext;
    private ISearchPagesNames currentActivity;
    TextView requestTxtBox;
    List<String> resultOfSearch=null;
    List<String> InnerNamesArticles = new ArrayList<>();
    List<String> InnerNamesSection = new ArrayList<>();

    @Override
    public void run() {
        super.run();
    }

    public SearchDialog(Context context){
        currentContext = context;
        currentActivity = (ISearchPagesNames) context;
        requestTxtBox = new EditText(context);
        GetSearchDialog();
    }

    private SearchDialog GetSearchDialog(){
        AlertDialog.Builder searchDialog = new AlertDialog.Builder(currentContext);

        searchDialog.setIcon(R.drawable.search)
                .setMessage("Поиск")
                .setPositiveButton("Начать поиск", this)
                .setView(requestTxtBox)
                .create()
                .show();

        return this;
    }

    public List<String> GetResult(){
        return resultOfSearch;
    }

    public List<String> GetAllSuccessArticles(){
        return InnerNamesArticles;
    }

    public List<String> GetAllSuccessSection(){
        return InnerNamesSection;
    }

    public String GetRequest(){
        return  requestTxtBox.getText().toString();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
       resultOfSearch = LoadPage(currentActivity.GetSearchSpace() , requestTxtBox.getText().toString());
       currentActivity.SearchCallBack(this);
    }

    private List<String> LoadPage(List<Map<String, String>> tableWrites, String request) {

        List<String> resultPagesList = new ArrayList<>();
        for (Map<String, String> tableWrite : tableWrites) {
            String pageContent=new SearchLogic(request)
                    .GetLinkedText(tableWrite.get("Paragraph"));
            if(pageContent!=null) {
                Map<String, String> section = Finder.GetByID("Section", Integer.parseInt(tableWrite.get("Section_id")));
                InnerNamesArticles.add(tableWrite.get("Name"));
                InnerNamesSection.add(section.get("Name"));
            }
            resultPagesList.add(pageContent);
        }

        return resultPagesList;
    }

}
