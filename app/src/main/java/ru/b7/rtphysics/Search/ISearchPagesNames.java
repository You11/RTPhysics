package ru.b7.rtphysics.Search;

import java.util.List;
import java.util.Map;

/**
 * Created by Домашний on 29.11.2015.
 */
public interface ISearchPagesNames {
    /*
     *All pages with content for searching must implement this
     *So when SearchDialog get a context of Activity it can Search correctly on current Context
     */
    List<Map<String,String>> GetSearchSpace();
    void SearchCallBack(SearchDialog dialog);
}
