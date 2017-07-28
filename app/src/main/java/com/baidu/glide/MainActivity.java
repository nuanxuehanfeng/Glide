package com.baidu.glide;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.glide.view.GlideRoundTransform;
import com.baidu.glide.view.MyImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.baidu.glide.R.drawable.b;

//AppCompatActivity(用来替换ActionBarActivity)与Activity的区别：界面最上面是否会出现一个ActionBar
public class MainActivity extends Activity {

    @BindView(R.id.activity_main_btn_1)
    Button activityMainBtn1;
    @BindView(R.id.activity_main_btn_2)
    Button activityMainBtn2;
    @BindView(R.id.activity_main_btn_3)
    Button activityMainBtn3;
    @BindView(R.id.activity_main_btn_4)
    Button activityMainBtn4;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.activity_main_iv_1)
    MyImageView activityMainIv1;
    @BindView(R.id.activity_main_iv_2)
    ImageView activityMainIv2;
    @BindView(R.id.activity_main_iv_3)
    ImageView activityMainIv3;
    private Unbinder bind;
    private ViewPropertyAnimation.Animator animationObject;
    private SimpleTarget target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        System.out.println("\"开始\" = " + "开始");

        //动画
        animationObject = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                view.setAlpha( 0f );
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat( view, "alpha", 0f, 1f );
                fadeAnim.setDuration( 2500 );
                fadeAnim.start();
            }
        };
        activityMainIv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityMainIv1.setOnClickListener(true);
            }
        });






    }

    @OnClick({R.id.activity_main_btn_1, R.id.activity_main_btn_2, R.id.activity_main_btn_3, R.id.activity_main_btn_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_main_btn_1:
                System.out.println("111 = " + 111);
                String url = "http://pic35.nipic.com/20131121/2531170_145358633000_2.jpg";
//                Glide内部会自动判断图片格式,支持加载GIF图片的
//                Picasso是不会支持加载GIF图片的。
//                String url = "http://p1.pstatp.com/large/166200019850062839d3";
//                注意with()方法中传入的实例会决定Glide加载图片的生命周期
//                load    Glide支持加载各种各样的图片资源，包括网络图片、本地图片、应用资源、二进制流、Uri对象等等
//                into()方法不仅仅是只能接收ImageView类型的参数，还支持很多更丰富的用法
//                placeholder      用来作为占位图显示
                Glide.with(this)
                        .load(url)
                        .asBitmap()//只允许加载静态图片，不需要Glide去帮我们自动进行图片格式的判断了。显示第一帧
                       // .asGif()   //如果指定了只能加载动态图片，而传入的图片却是一张静图的话，那么结果自然就只有加载失败喽。显示error 图片
                        .placeholder(R.drawable.a)
                        .error(b)//异常占位图就是指，如果因为某些异常情况导致图片加载失败，比如说手机网络信号不好，这个时候就显示这张异常占位图。
                        .diskCacheStrategy(DiskCacheStrategy.NONE)  //并传入DiskCacheStrategy.NONE参数，这样就可以禁用掉Glide的缓存功能。
                    //    .override(800, 800)  //  因为Glide会自动根据ImageView的大小来决定图片的大小。避免图片内存浪费，甚至是内存溢出的问题。指定大小(有时候不好用)
                        .into(activityMainIv1);
                break;
            case R.id.activity_main_btn_2:


                String url1 = "http://pic35.nipic.com/20131121/2531170_145358633000_2.jpg";
                Glide.with(this)
                        .load(url1)
                        //优先级别
//                Priority.LOW
//                Priority.NORMAL
//                Priority.HIGH
//                Priority.IMMEDIATE
                        .priority( Priority.HIGH )
                     //   .placeholder(R.drawable.a)
                       // .override(20, 20)  //  因为Glide会自动根据ImageView的大小来决定图片的大小。避免图片内存浪费，甚至是内存溢出的问题。指定大小(有时候不好用)
                      //  .animate(animationObject)//添加动画
                      //  .animate(R.anim.anim1)//添加动画
                     //   .thumbnail(0.1f)   //缩放
                      //  .centerCrop()//CenterCrop()会缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                       // .fitCenter()//fitCenter()会缩放图片让两边都相等或小于ImageView的所需求的边框。图片会被完整显示，可能不能完全填充整个ImageView。
                        .skipMemoryCache(true)//这个只掉过内存缓存！Glide为了避免以后的网络请求，仍然会缓存到磁盘。
                        .diskCacheStrategy( DiskCacheStrategy.NONE )//禁止请求的磁盘缓存，使用枚举型变量DiskCacheStrategy.NONE作为参数。
//                DiskCacheStrategy.NONE 啥也不缓存
//                DiskCacheStrategy.SOURCE 只缓存全尺寸图. 上面例子里的1000x1000像素的图片
//                DiskCacheStrategy.RESULT 只缓存最终降低分辨后用到的图片
//                DiskCacheStrategy.ALL 缓存所有类型的图片 (默认行为)

                        .into(activityMainIv1);
                System.out.println("111 = " + 222);
                break;
            case R.id.activity_main_btn_3:
//                清除缓存的方法??
                Glide.get(this).clearMemory(); //主线程运行
                System.out.println("111 = " + 333);
                break;
            case R.id.activity_main_btn_4:
                String url2 = "http://pic35.nipic.com/20131121/2531170_145358633000_2.jpg";
//                请确保将你的回调定义为一个字段对象，防止被万恶的Android垃圾回收给清理掉。
//                如果你的请求需要在activity的生命周期以外  使用context.getApplicationContext()
//                100,100  可以指定 大小   影响清晰度呢
                target = new SimpleTarget<Bitmap>(200,200) {
                   @Override public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation)  {

                       activityMainIv1.setImageBitmap( bitmap );
                   }
                };
                Glide.with(this)
                        .load(url2)
                        //这两个一起使用，不然可能会解析出gif 会报错的
                        .asBitmap()
                        .transform(new GlideRoundTransform(MainActivity.this,30))
                        .into(target);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
