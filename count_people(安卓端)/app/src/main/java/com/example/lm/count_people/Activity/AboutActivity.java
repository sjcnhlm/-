package com.example.lm.count_people.Activity;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lm.count_people.BuildConfig;
import com.example.lm.count_people.R;



import me.drakeet.multitype.Items;
import me.drakeet.support.about.AbsAboutActivity;
import me.drakeet.support.about.Card;
import me.drakeet.support.about.Category;
import me.drakeet.support.about.Contributor;
import me.drakeet.support.about.License;


public class AboutActivity  extends AbsAboutActivity {

    @Override
    protected void onCreateHeader(@NonNull ImageView icon, @NonNull TextView slogan, @NonNull TextView version) {
        icon.setImageResource(R.mipmap.ic_launcher_my);
        slogan.setText("About Page By lm");
        version.setText("v" + BuildConfig.VERSION_NAME);
    }

    @Override
    protected void onItemsCreated(@NonNull Items items) {
        items.add(new Category("介绍与帮助"));
        items.add(new Card(getString(R.string.card_content)));
        items.add(new Category("Developers"));
        items.add(new Contributor(R.drawable.tx1, "诗酒趁年华", "Developer & designer", "http://weibo.com/drak11t"));

        items.add(new Category("Open Source Licenses"));
        items.add(new License("MultiType", "drakeet", License.APACHE_2, "https://github.com/drakeet/MultiType"));
        items.add(new License("about-page", "drakeet", License.APACHE_2, "https://github.com/drakeet/about-page"));
    }




}
