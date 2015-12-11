package ru.b7.rtphysics.Search;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.Database.Access_API.Finder;

/**
 * Created by Домашний on 04.12.2015.
 */
public class SearchViewProcesser {

    List<String> InnerNamesArticles = new ArrayList<>();
    List<String> InnerNamesSection = new ArrayList<>();
    List<String> Content;

    public SearchViewProcesser(Context context,String request) {
        ISearchPagesNames currentActivity = (ISearchPagesNames) context;
        Content = LoadPage(currentActivity.GetSearchSpace(), request);

    }

    public List<String> GetAllSuccessArticles(){
        return InnerNamesArticles;
    }

    public List<String> GetAllSuccessSection(){
        return InnerNamesSection;
    }

    public void CallBackActivate(){
    //    currentActivity.SearchCallBack(this);
    }

    private List<String> LoadPage(List<Map<String, String>> tableWrites, String request) {
        List<String> resultPagesList = new ArrayList<>();

        for (Map<String, String> tableWrite : tableWrites) {
            String pageContent=new SearchLogic(request)
                    .GetLinkedText(tableWrite.get("Paragraph"));

            InnerNamesArticles.add(tableWrite.get("Name"));
            Map<String,String> section = Finder.GetByID("Section", Integer.parseInt(tableWrite.get("Section_id")));
            InnerNamesSection.add(section.get("Name"));

            resultPagesList.add(pageContent);
        }

        return resultPagesList;
    }

}
