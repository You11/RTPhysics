package ru.b7.rtphysics.ScreenElements;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import  ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.R;

/**
 * Created by Nikita on 12.11.2015.
 */

public class MenuParagraph extends StyleGlobal {

    TagSetter tag;
    List<Map<String,String>> FormulasInfo;

    public MenuParagraph(BaseActivity activity, TagSetter tag,List<Map<String,String>> formulasInfo) {
        super(activity, "Formula");

        FormulasInfo = formulasInfo;
        this.tag = tag;

    }

    @Override
    public View buildMainLayout() {

            ScrollView scroll = new ScrollView(parentContext);

            String paragraphText = Finder.GetByID("Articles", tag.id).get("Paragraph");
            LinearLayout lay = super.buildWidgetsOnLay(LayoutCreate(paragraphText));
            scroll.addView(lay);

            return scroll;

    }


    private List<View> LayoutCreate(String content) {

        TextView paragraphPage = new TextView(new ContextThemeWrapper(parentContext, R.style.ArticleTextStyle));
        paragraphPage.setPadding(0, 20, 0, 0);
        paragraphPage.setText(Html.fromHtml(content, new MyImageGetter(parentContext), null));

        List<View> elements = new ArrayList<>();
        elements.add(paragraphPage);

        return elements;
    }

    public class MyImageGetter implements Html.ImageGetter {

        Context c;

        public MyImageGetter(Context c)
        {
            this.c = c;
        }

        @Override public Drawable getDrawable(String source) {

            Drawable drawFromPath;

            int path = c.getResources().getIdentifier(source, "drawable", c.getPackageName());

            drawFromPath = c.getResources().getDrawable(path);
            drawFromPath.setBounds(0, 0, drawFromPath.getIntrinsicWidth(),
                    drawFromPath.getIntrinsicHeight());

            return drawFromPath;
        }
    }

}
