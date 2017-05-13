package com.sp.tojoin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sp.tojoin.IListener.IEditActivity;
import com.sp.tojoin.presenter.EditPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/8.
 */

public class EditActivity extends Activity implements IEditActivity{

    private EditPresenter presenter=new EditPresenter(this);

    @BindView(R.id.edit_title)EditText editText_title;

    @BindView(R.id.edit_content)EditText editText_content;

    @BindView(R.id.button_back)Button button_bcak;

    @BindView(R.id.button_passage)Button button_passage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        button_passage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_title.getText()!=null&&editText_content!=null){
                    String title=editText_title.getText().toString();
                    String content=editText_content.getText().toString();
                    presenter.publisPassage("ufcab9ff029cd11e79762e72f53de85e3",title,content);
                }
            }
        });
        button_bcak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
