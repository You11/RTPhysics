package ru.b7.rtphysics.ScreenElements;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.R;

/**
 * Created by Nikita on 12.11.2015.
 */

public class MenuParagraph extends StyleGlobal {

    TagSetter tag;
    String content;
    SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(parentContext);


    public MenuParagraph(BaseActivity activity, TagSetter tag) {
        super(activity, "Formula");

        this.tag = tag;
        content = Finder.GetByID("Articles", tag.id).get("Paragraph");

    }

    public MenuParagraph(BaseActivity activity,String content){
        super(activity,null);
        this.content=content;
    }

    @Override
    public View buildMainLayout() {

        ScrollView scroll = new ScrollView(parentContext);
        LinearLayout lay = super.buildWidgetsOnLay(LayoutCreate(content));
        scroll.addView(lay);

        return scroll;
    }

    private List<View> LayoutCreate(String content) {

        JellyBeanSpanFixTextView paragraphPage = new JellyBeanSpanFixTextView(super.parentContext);

        paragraphPage.setPadding(0, 20, 0, 0);
        paragraphPage.setText(Html.fromHtml(content, new MyImageGetter(parentContext), null));
        paragraphPage.setTextSize(Integer.parseInt(sPref.getString("TEXT_SIZE_IN_ARTICLE", "10")));
        String color = sPref.getString("TEXT_COLOR_IN_ARTICLE", "R.color.handbookGrayTextColor");

        switch (color) {
            case "R.color.handbookGoldenTextColor":
                paragraphPage.setTextColor(ContextCompat.getColor(parentContext, R.color.handbookGoldenTextColor));
                break;
            case "R.color.handbookGrayTextColor":
                paragraphPage.setTextColor(ContextCompat.getColor(parentContext, R.color.handbookGrayTextColor));
                break;
            case "R.color.handbookWhiteTextColor":
                paragraphPage.setTextColor(ContextCompat.getColor(parentContext, R.color.handbookWhiteTextColor));
                break;
            default:
                paragraphPage.setTextColor(ContextCompat.getColor(parentContext, R.color.handbookGrayTextColor));
        }
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

        @Override
        public Drawable getDrawable(String source) {

               Drawable drawFromPath;
               int path = c.getResources().getIdentifier(source, "drawable", c.getPackageName());
               System.out.println(path);
               drawFromPath = c.getResources().getDrawable(path);
               drawFromPath.setBounds(0, 0, drawFromPath.getIntrinsicWidth(),
                       drawFromPath.getIntrinsicHeight());

               return drawFromPath;

        }
    }

    public class PatchedTextView extends TextView {
        public PatchedTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }
        public PatchedTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        public PatchedTextView(Context context) {
            super(context);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            try{
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }catch (ArrayIndexOutOfBoundsException e){
                setText(getText().toString());
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
        @Override
        public void setGravity(int gravity){
            try{
                super.setGravity(gravity);
            }catch (ArrayIndexOutOfBoundsException e){
                setText(getText().toString());
                super.setGravity(gravity);
            }
        }
        @Override
        public void setText(CharSequence text, BufferType type) {
            try{
                super.setText(text, type);
            } catch (ArrayIndexOutOfBoundsException e) {
                setText(text.toString());
            }
        }
    }
}
