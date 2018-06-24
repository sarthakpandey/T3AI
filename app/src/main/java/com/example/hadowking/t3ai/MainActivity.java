package com.example.hadowking.t3ai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int width = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int hieght = ViewGroup.LayoutParams.WRAP_CONTENT;

    private Button resetButton;

    private Button aiButton;

    private TableLayout mTableLayout;

    private TextView resultTextView;

    private Board gameBoard;

    private Boolean cpuControl = false;

    public static int counter = 1;

    public AI aiObject = new AI();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Tic Tac Toe And AI");

        initGame();

        gameBoard = new Board();

        resetButton = findViewById(R.id.resetButton);

        aiButton = findViewById(R.id.aiButton);

       // mTableLayout = findViewById(R.id.rootTable);

        resultTextView = findViewById(R.id.resultTextView);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        aiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cpuControl = true;

                int position = aiObject.miniMax(gameBoard.copy()).position;

                counter  = 0;

                if(position != 0){

                    (mTableLayout.findViewWithTag(position)).callOnClick();

                }

                cpuControl = false;

            }
        });

    }

    private void resetGame() {

        mTableLayout.removeAllViews();
        resultTextView.setText("Result");
        initGame();
        gameBoard = new Board();
        aiObject = new AI();

    }

    private void initGame() {

        mTableLayout = findViewById(R.id.rootTable);

        mTableLayout.setStretchAllColumns(true);

        int counter = 1;

        for(int row = 0; row < 3; row++){

            TableRow tableRow = new TableRow(this);

            for(int col = 0; col < 3; col++){

                Button button = new Button(this);

                button.setTag(counter);

                button.setOnClickListener(this);
                button.setWidth(300);
                button.setHeight(300);
                button.setTextSize(40);

                tableRow.addView(button);

                counter++;

            }



            mTableLayout.addView(tableRow, new TableLayout.LayoutParams(width, hieght));

        }



    }

    @Override
    public void onClick(View view) {

        if(gameBoard.gameOver)
            return;

        String player1 = "O", player2 = "X", place = "";

            if(gameBoard.getCurrentPlayer() == 1)
                place = player1;
            else
                place = player2;



        int choice = Integer.valueOf(view.getTag().toString());

        List<Integer> available = gameBoard.getAvailabaloSlots();

        Boolean flag = true;

        for(int i = 0; i < available.size(); i++){

            if(choice != available.get(i)){

                flag = false;

            }else{

                flag = true;

                break;

            }

        }

        if(!flag)
            return;

        gameBoard.placePiece(choice);

        ((Button)view).setText(place);

        updateGame();

        /*if(!cpuControl){

            aiButton.callOnClick();

        }*/



    }

    private void updateGame() {

        //Toast.makeText(MainActivity.this,gameBoard.getGameResults() + "", Toast.LENGTH_SHORT).show();

        switch(gameBoard.getGameResults()){

            case 1 : resultTextView.setText("Player1 Wins :)"); break;
            case 2 : resultTextView.setText("Player2 Wins :)"); break;
            case 0 : resultTextView.setText("Draw! Good Game :P"); break;
            default:break;

        }
    }
}
