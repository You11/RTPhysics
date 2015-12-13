package ru.b7.rtphysics.Calculator.FCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.R;

public class FCalculatorMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fcalculator_activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        int numberOfFormula = intent.getIntExtra("numberOfFormula", -1);
        String nameOfTheArticle = intent.getStringExtra("nameOfArticle");

        final FCalculatorLayoutView layoutCreator = new FCalculatorLayoutView(this);

        final String formula = layoutCreator.getFormula(nameOfTheArticle, numberOfFormula);
        final int numberOfVariables = layoutCreator.getNumberOfVariables();

        //главный слой
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.fcalculator_layout);
        linearLayout.addView(layoutCreator.createScrollView(numberOfVariables));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] userInput = layoutCreator.getUserInput(numberOfVariables);
                int variable = layoutCreator.checkUserInput(userInput);

                switch (variable) {
                    case -2:
                        Toast.makeText(
                                FCalculatorMainActivity.this,
                                "Не найдено поле для вывода данных",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(
                                FCalculatorMainActivity.this,
                                "Введите больше значений",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        FCalculatorCalculations calc = new FCalculatorCalculations(
                                FCalculatorMainActivity.this);
                        calc.getInput(formula);
                        String answer = calc.calculateAnswer(userInput);
                        layoutCreator.inputAnswer(answer);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
