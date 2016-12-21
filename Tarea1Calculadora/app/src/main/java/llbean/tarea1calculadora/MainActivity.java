package llbean.tarea1calculadora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView displayText;
    private TextView resultText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);

        initialize();
    }

    /**
     * Initialize UI components
     */
    private void initialize() {
        final Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new BtnListener('1'));

        final Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new BtnListener('2'));

        final Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new BtnListener('3'));

        final Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new BtnListener('4'));

        final Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new BtnListener('5'));

        final Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new BtnListener('6'));

        final Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new BtnListener('7'));

        final Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new BtnListener('8'));

        final Button btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new BtnListener('9'));

        final Button btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(new BtnListener('0'));

        final Button dot = (Button) findViewById(R.id.dot);
        dot.setOnClickListener(new BtnListener('.')); //TODO: still not validated

        final Button btnEquals = (Button) findViewById(R.id.equals);
        btnEquals.setOnClickListener(new BtnListener('='));

        final Button btnDivide = (Button) findViewById(R.id.divide);
        btnDivide.setOnClickListener(new BtnListener('/'));

        final Button btnMinus = (Button) findViewById(R.id.minus);
        btnMinus.setOnClickListener(new BtnListener('-'));

        final Button btnMulti = (Button) findViewById(R.id.multi);
        btnMulti.setOnClickListener(new BtnListener('*'));

        final Button btnPlus = (Button) findViewById(R.id.plus);
        btnPlus.setOnClickListener(new BtnListener('+'));

        final Button btnReset = (Button) findViewById(R.id.reset);
        btnReset.setOnClickListener(new BtnListener('~'));

        displayText = (TextView) findViewById(R.id.display);
        displayText.setText("");

        resultText = (TextView) findViewById(R.id.result);
        resultText.setText("");
    }

    /**
     * Listener for the buttons in the mainActivity
     */
    private class BtnListener implements View.OnClickListener{

        // current selected/pressed digit
        private char currDigit;

        public BtnListener(final char aDigit) {
            currDigit = aDigit;
        }

        /**
         * Do the math operator and return the result as an String
         *
         * @param num1
         * @param num2
         * @param operator
         * @return String with the result
         */
        private String doOperation(final Double num1, final Double num2, final char operator) {
            String result = "";

            switch (operator) {
                case '+':
                    result = (num1 + num2) + "";
                    break;
                case '-':
                    result = (num1 - num2) + "";
                    break;
                case '/':
                    if (num2 != 0) {
                        result = (num1 / num2) + "";
                    } else {
                        result = "ERROR";
                    }
                    break;
                case '*':
                    result = (num1 * num2) + "";
                    break;
                default:
                    result = num1 + "";
                    break;
            }

            return result;
        }


        @Override
        public void onClick(View v) {
            try {
                final String currText = displayText.getText() + "";

                switch (currDigit) {
                    case '=':
                        // split the text and get the numbers
                        final String[] textArray = currText.split("\\D"); //TODO: include the dot
                        final String num1 = textArray[0];
                        final String num2;

                        // validates the second number is there
                        if (textArray.length > 1) {
                            num2 = textArray[1];
                        } else {
                            num2 = "0";
                        }

                        final String operStr = currText.replaceFirst(num1,"").replaceFirst(num2,"");
                        char oper = '?';

                        if (!operStr.isEmpty()) {
                            oper = operStr.charAt(0);
                        }

                        String result = doOperation(Double.valueOf(num1), Double.valueOf(num2), oper);

                        resultText.setText(" = " + result);
                        //resultText.setText(num1 + oper + num2 + " = " + result);
                        //displayText.setText("");
                        break;
                    case '~':
                        // reset button
                        displayText.setText("");
                        resultText.setText("");
                        break;
                    default:
                        // any other button should clear all
                        displayText.setText(currText + currDigit);
                        resultText.setText("");
                        break;
                }
            } catch (Exception e) {
                // in case of error, just reset
                displayText.setText("");
                resultText.setText("");
            }
        }
    }

}
