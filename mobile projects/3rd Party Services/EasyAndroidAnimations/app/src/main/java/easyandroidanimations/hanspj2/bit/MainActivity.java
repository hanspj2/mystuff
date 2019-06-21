package easyandroidanimations.hanspj2.bit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.ExplodeAnimation;
import com.easyandroidanimations.library.FlipVerticalAnimation;
import com.easyandroidanimations.library.FlipVerticalToAnimation;
import com.easyandroidanimations.library.ScaleInAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView=(ImageView)findViewById(R.id.imageView);
        final ImageView imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ExplodeAnimation(imageView).animate();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ScaleInAnimation(imageView2).animate();
            }
        });
    }
}
