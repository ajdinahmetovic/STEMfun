package space.stemfun.stemfun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.stem.spacev1.UnityPlayerActivity;

import com.google.gson.Gson;

public class RandomGenerator extends AppCompatActivity {
    ImageView image;
    final int randoms[] = new int[5];
    int k = 0;
    int random;
    boolean finn = false;
    int previous[] = new int[2];
    int imgs [] = new int[5];
    String fields [] = new String[5];
    TextView text;

    TinyDB localDb;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_generator);
        image = findViewById(R.id.image);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        fields [0] = "null";
        fields [1] = "Science";
        fields [2] = "Technology";
        fields [3] = "Engineering";
        fields [4] = "Math";
        final Field [] fieldsE = {Field.SCIENCE, Field.TECHNOLOGY,Field.ENGINEERING, Field.MATH};

        localDb = new TinyDB(this);

        intent = new Intent(this, UnityPlayerActivity.class);

        image = findViewById(R.id.image);
        text = findViewById(R.id.text);

        final Animation zoomin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        final Animation zoomout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomout);
        //image.startAnimation(zoomin);

        previous[0] = (int) (Math.random() * 4 + 1);
        previous[1] = (int) (Math.random() * 4 + 1);
        for (int i = 0; i < (int) 5; i++) {
            random = (int) (Math.random() * 4 + 1);
            //System.out.println(random);
            if (previous[0] == random || previous[1] == random) {
                i--;
            } else {
                // System.out.println(random);
                previous[0] = previous[1];
                previous[1] = random;
                randoms[i] = random;

                switch (random) {
                    case 1:
                        imgs[i] =R.drawable.science;
                        //img.setImageDrawable(getDrawable(R.drawable.science));
                    case 2:
                        imgs[i] = R.drawable.tech_icon;
                        //img.setImageDrawable(getDrawable(R.drawable.technology));
                    case 3:
                        imgs[i] = R.drawable.engineering;
                        //img.setImageDrawable(getDrawable(R.drawable.engineering));
                    case 4:
                        imgs[i] = R.drawable.math;
                        //img.setImageDrawable(getDrawable(R.drawable.math));
                }

            }
        }

        for( int i = 0;i<5;i++){
            System.out.println(randoms[i]);
        }


        if(randoms[0]==1){
            image.setImageResource(R.drawable.science);
        } else if(randoms[0]==2) {
            image.setImageResource(R.drawable.tech_icon);
        } else if(randoms[0]==3) {
            image.setImageResource(R.drawable.engineering);
        } else if(randoms[0]==4) {
            image.setImageResource(R.drawable.math);
        }

        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                zoomout.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {


                        if(randoms[1]==1){
                            image.setImageResource(R.drawable.science);
                        } else if(randoms[1]==2) {
                            image.setImageResource(R.drawable.tech_icon);
                        } else if(randoms[1]==3) {
                            image.setImageResource(R.drawable.engineering);
                        } else if(randoms[1]==4) {
                            image.setImageResource(R.drawable.math);
                        }
                        zoomin.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                                zoomout.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {


                                        if(randoms[2]==1){
                                            image.setImageResource(R.drawable.science);
                                        } else if(randoms[2]==2) {
                                            image.setImageResource(R.drawable.tech_icon);
                                        } else if(randoms[2]==3) {
                                            image.setImageResource(R.drawable.engineering);
                                        } else if(randoms[2]==4) {
                                            image.setImageResource(R.drawable.math);
                                        }
                                        zoomin.setAnimationListener(new Animation.AnimationListener() {
                                            @Override
                                            public void onAnimationStart(Animation animation) {
                                            }

                                            @Override
                                            public void onAnimationEnd(Animation animation) {

                                                zoomout.setAnimationListener(new Animation.AnimationListener() {
                                                    @Override
                                                    public void onAnimationStart(Animation animation) {

                                                    }

                                                    @Override
                                                    public void onAnimationEnd(Animation animation) {
                                                        //
                                                        if(randoms[3]==1){
                                                            image.setImageResource(R.drawable.science);
                                                        } else if(randoms[2]==2) {
                                                            image.setImageResource(R.drawable.tech_icon);
                                                        } else if(randoms[3]==3) {
                                                            image.setImageResource(R.drawable.engineering);
                                                        } else if(randoms[3]==4) {
                                                            image.setImageResource(R.drawable.math);
                                                        }
                                                        zoomin.setAnimationListener(new Animation.AnimationListener() {
                                                            @Override
                                                            public void onAnimationStart(Animation animation) {
                                                            }

                                                            @Override
                                                            public void onAnimationEnd(Animation animation) {

                                                                zoomout.setAnimationListener(new Animation.AnimationListener() {
                                                                    @Override
                                                                    public void onAnimationStart(Animation animation) {

                                                                    }

                                                                    @Override
                                                                    public void onAnimationEnd(Animation animation) {

                                                                        //
                                                                        if(randoms[4]==1){
                                                                            image.setImageResource(R.drawable.science);
                                                                        } else if(randoms[4]==2) {
                                                                            image.setImageResource(R.drawable.tech_icon);
                                                                        } else if(randoms[4]==3) {
                                                                            image.setImageResource(R.drawable.engineering);
                                                                        } else if(randoms[4]==4) {
                                                                            image.setImageResource(R.drawable.math);
                                                                        }
                                                                        zoomin.setAnimationListener(new Animation.AnimationListener() {
                                                                            @Override
                                                                            public void onAnimationStart(Animation animation) {
                                                                            }

                                                                            @Override
                                                                            public void onAnimationEnd(Animation animation) {
                                                                                text.setText(fields[randoms[4]]);

                                                                                localDb.putObject("currentField", fieldsE[randoms[4]-1]);

                                                                                if(fields[randoms[4]].toLowerCase().equals("technology")){
                                                                                    /*
                                                                                    intent.putExtra("gameName", "techScene");
                                                                                    startActivity(intent);
                                                                                    */
                                                                                    finish();
                                                                                }else {
                                                                                    /*
                                                                                    intent.putExtra("gameName", fields[randoms[4]].toLowerCase() + "Scene");
                                                                                    startActivity(intent);
                                                                                    */
                                                                                    finish();
                                                                                    System.out.println(fields[randoms[4]].toLowerCase() + "Scene");
                                                                                }


                                                                            }

                                                                            @Override
                                                                            public void onAnimationRepeat(Animation animation) {

                                                                            }
                                                                        });
                                                                        image.startAnimation(zoomin);


                                                                    }

                                                                    @Override
                                                                    public void onAnimationRepeat(Animation animation) {

                                                                    }
                                                                });
                                                                image.startAnimation(zoomout);
                                                            }

                                                            @Override
                                                            public void onAnimationRepeat(Animation animation) {

                                                            }
                                                        });
                                                        image.startAnimation(zoomin);

                                                    }

                                                    @Override
                                                    public void onAnimationRepeat(Animation animation) {

                                                    }
                                                });
                                                image.startAnimation(zoomout);
                                            }

                                            @Override
                                            public void onAnimationRepeat(Animation animation) {

                                            }
                                        });
                                        image.startAnimation(zoomin);

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                image.startAnimation(zoomout);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        image.startAnimation(zoomin);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                image.startAnimation(zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(zoomin);

    }
}
