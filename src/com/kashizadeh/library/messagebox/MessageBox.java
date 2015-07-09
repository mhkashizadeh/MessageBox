package com.kashizadeh.library.messagebox;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;


public class MessageBox {

    public static final int              LENGTH_SHORT       = 3000;
    public static final int              LENGTH_LONG        = 5000;

    private static ArrayList<MessageBox> messageBoxlist     = new ArrayList<MessageBox>();
    private static boolean               isShow;
    private static Typeface              typefaceIconFont;

    private Handler                      handler            = new Handler();

    private WindowManager                windowManager;
    private Context                      context;

    private View                         layout;
    private FrameLayout                  lytMessageBox;
    private TextView                     txtMessage;
    private TextView                     txtIcon;

    private int[]                        animations         = new int[]{ R.anim.fade_in, R.anim.fade_out };

    private String                       message;
    private int                          icon;
    private int                          length;
    private int                          gravity            = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
    private int                          backgroundDrawable = R.drawable.shape_message_box_1;


    private MessageBox(Context context) {
        this.context = context;
        initiliazer();
    }


    private void initiliazer() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.layout_message_box, null);
        lytMessageBox = (FrameLayout) layout.findViewById(R.id.lytMessageBox);
        txtMessage = (TextView) layout.findViewById(R.id.txtMessage);
        txtIcon = (TextView) layout.findViewById(R.id.txtIcon);

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (typefaceIconFont == null) {
            typefaceIconFont = Typeface.createFromAsset(context.getAssets(), "icomoon.ttf");
        }
        txtIcon.setTypeface(typefaceIconFont);
    }


    public static MessageBox makeMessage(Context context, String message, int length) {
        MessageBox messageBox = new MessageBox(context);
        messageBox.message = message;
        messageBox.length = length;

        if (isShow) {
            messageBoxlist.add(messageBox);
        }
        return messageBox;
    }


    public MessageBox setGravity(int gravity) {
        if (lytMessageBox == null) {
            return null;
        }
        if (messageBoxlist.contains(this)) {
            messageBoxlist.get(messageBoxlist.indexOf(this)).gravity = gravity;
        }

        this.gravity = gravity;

        return this;

    }


    public MessageBox setBackground(int drawable) {
        if (lytMessageBox == null) {
            return null;
        }

        if (messageBoxlist.contains(this)) {
            messageBoxlist.get(messageBoxlist.indexOf(this)).backgroundDrawable = drawable;
        }

        backgroundDrawable = drawable;

        return this;
    }


    public MessageBox setIcon(int icon) {
        if (lytMessageBox == null) {
            return null;
        }
        if (messageBoxlist.contains(this)) {
            messageBoxlist.get(messageBoxlist.indexOf(this)).icon = icon;
        }

        this.icon = icon;

        return this;
    }


    public MessageBox setMessage(String message) {
        if (lytMessageBox == null) {
            return null;
        }
        if (messageBoxlist.contains(this)) {
            messageBoxlist.get(messageBoxlist.indexOf(this)).message = message;
        }

        this.message = message;

        return this;
    }


    private void checkMessageBoxList() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (messageBoxlist.size() != 0) {
                    final MessageBox messageBox = messageBoxlist.get(0);
                    if ( !isShow) {
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                messageBox.show();
                            }
                        });
                        messageBoxlist.remove(messageBox);
                    }

                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    public void show() {
        if (isShow) {
            if (messageBoxlist.size() == 1) {
                checkMessageBoxList();
            }
            return;
        }

        isShow = true;

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, gravity);

        lytMessageBox.setBackgroundDrawable(context.getResources().getDrawable(backgroundDrawable));
        lytMessageBox.setLayoutParams(layoutParams);

        if (icon != 0) {
            txtIcon.setText(icon);
        }

        txtMessage.setText(message);

        WindowManager.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, LayoutParams.TYPE_SYSTEM_OVERLAY, LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, PixelFormat.TRANSLUCENT);
        windowManager.addView(layout, params);

        lytMessageBox.startAnimation(AnimationUtils.loadAnimation(context, animations[0]));
        hide();
    }


    public void hide() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Animation fade_out = AnimationUtils.loadAnimation(context, animations[1]);
                fade_out.setAnimationListener(new AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {}


                    @Override
                    public void onAnimationRepeat(Animation animation) {}


                    @Override
                    public void onAnimationEnd(Animation animation) {
                        windowManager.removeView(layout);
                        isShow = false;
                    }
                });

                lytMessageBox.startAnimation(fade_out);

            }
        }, length);
    }
}
