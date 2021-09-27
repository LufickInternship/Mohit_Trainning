package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  {

    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char EQU = '=';
    private final char MODULUS = '%';
    private char ACTION;
    private double val1 = Double.NaN;
    private double val2;

    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;

    Button b_clear;
    Button buttonPercent;
    Button b_divide;
    Button b_multi;
    Button b_sub;
    Button b_add;
    Button b_equal;
    Button b_dot;

    TextView t,t2;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewsetup();

        b1.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "1");
        });

        b2.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "2");
        });

        b3.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "3");
        });

        b4.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "4");
        });

        b5.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "5");
        });

        b6.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "6");
        });

        b7.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "7");
        });

        b8.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "8");
        });

        b9.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "9");
        });

        b0.setOnClickListener(view -> {
            ifErrorOnOutput();
            exceedLength();
            t.setText(t.getText().toString() + "0");
        });

        b_dot.setOnClickListener(view -> {
            exceedLength();
            t.setText(t.getText().toString() + ".");
        });


        b_add.setOnClickListener(view -> {
            if (t.getText().length() > 0) {
                ACTION = ADDITION;
                operation();
                if (!ifReallyDecimal()) {
                    t2.setText(val1 + "+");
                } else {
                    t2.setText((int) val1 + "+");
                }
               t.setText(null);
            } else {
                t.setText("Error");//t2
            }
        });

        b_sub.setOnClickListener(view -> {
            if (t.getText().length() > 0) {
                ACTION = SUBTRACTION;
                operation();
                if (t.getText().length() > 0)
                    if (!ifReallyDecimal()) {
                        t2.setText(val1 + "-");
                    } else {
                        t2.setText((int) val1 + "-");
                    }
                t.setText(null);
            } else {
                t.setText("Error");
            }
        });

        b_multi.setOnClickListener(view -> {
            if (t.getText().length() > 0) {
                ACTION = MULTIPLICATION;
                operation();
                if (!ifReallyDecimal()) {
                    t2.setText(val1 + "×");
                } else {
                    t2.setText((int) val1 + "×");
                }
                t.setText(null);
            } else {
                t2.setText("Error");
            }
        });

        b_divide.setOnClickListener(view -> {
            if (t.getText().length() > 0) {
                ACTION = DIVISION;
                operation();
                if (!ifReallyDecimal()) {
                    t2.setText(val1 + "/");
                } else {
                    t2.setText((int) val1 + "/");
                }
            } else {
                t.setText("Error");
            }
        });

        b_equal.setOnClickListener(view -> {
            if (t.getText().length() > 0) {
                operation();
                ACTION = EQU;
                /*t2.getText().toString() + String.valueOf(val2) + "=" + */
                t2.setText(String.valueOf(val1));
                t.setText(null);
            } else {
                t2.setText("Error");
            }
        });

        b_clear.setOnClickListener(view -> {
            if (t.getText().length() > 0) {
                CharSequence name = t.getText().toString();
                t.setText(name.subSequence(0, name.length() - 1));
            } else {
                val1 = Double.NaN;
                val2 = Double.NaN;
                t.setText("");
                t2.setText("");
            }
        });


        b_clear.setOnLongClickListener(view -> {
            val1 = Double.NaN;
            val2 = Double.NaN;
            t.setText("");
            t2.setText("");
            return true;
        });
    }

    private void viewsetup()
    {
        b0 = (Button) findViewById(R.id.button_zero);
        b1 = (Button) findViewById(R.id.button_one);
        b2 = (Button) findViewById(R.id.button_two);
        b3 = (Button) findViewById(R.id.button_three);
        b4 = (Button) findViewById(R.id.button_four);
        b5 = (Button) findViewById(R.id.button_five);
        b6 = (Button) findViewById(R.id.button_six);
        b7 = (Button) findViewById(R.id.button_seven);
        b8 = (Button) findViewById(R.id.button_eight);
        b9 = (Button) findViewById(R.id.button_nine);

        b_clear = (Button) findViewById(R.id.button_clear);
        buttonPercent = (Button) findViewById(R.id.button_percent);
        b_divide = (Button) findViewById(R.id.button_division);
        b_multi = (Button) findViewById(R.id.button_multiplication);
        b_sub = (Button) findViewById(R.id.button_subtraction);
        b_add = (Button) findViewById(R.id.button_addition);
        b_equal = (Button) findViewById(R.id.button_equal);
        b_dot = (Button) findViewById(R.id.button_dot);
        t = (TextView) findViewById(R.id.textView_input_numbers);
        t2 = (TextView) findViewById(R.id.textView_output_numbers);
    }

    private void operation() {
        if (!Double.isNaN(val1)) {
            if (t2.getText().toString().charAt(0) == '-') {
                val1 = (-1) * val1;
            }
            val2 = Double.parseDouble(t.getText().toString());

            switch (ACTION) {
                case ADDITION:
                    val1 = val1 + val2;
                    break;
                case SUBTRACTION:
                    val1 = val1 - val2;
                    break;
                case MULTIPLICATION:
                    val1 = val1 * val2;
                    break;
                case DIVISION:
                    if (val2 == 0) {
                        val1 = 0.0;
                    } else {
                        val1 /= val2;
                    }
                    break;
                case MODULUS:
                    val1 = val1 % val2;
                    break;
                case EQU:
                    break;
            }
        } else {
            val1 = Double.parseDouble(t.getText().toString());
        }
    }

    private void ifErrorOnOutput() {
        if (t2.getText().toString().equals("Error")) {
            t.setText("");
        }
    }

    private boolean ifReallyDecimal() {
        return val1 == (int) val1;
    }


    private void exceedLength() {
        if (t.getText().toString().length() > 10) {
            t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
    }



}