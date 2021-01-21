
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Scanner;

import static javafx.application.Application.launch;
import static javafx.scene.paint.Color.*;
import static javafx.scene.text.Font.font;

/**
 * This class is to use javaFX to draw a bar chart and display it to the user based on the information
 * user input
 * @Author:Zhiping Yu, student# 000822513
 * @Date: 2020-06-01
 */
public class Diagram extends Application {

    public static final int WINDOW_WIDTH = 600; // the width of canvas in pixels
    public static final int WINDOW_HEIGHT = 600; // the height of canvas in pixels
    public static final double SIZE_TITLE = 30; //  graph's title size
    public static final double SIZE_TEXT = 16; //  graph's content size
    public static final int HEAD_X = 200; // graph's title x-coordinate
    public static final int HEAD_Y = 50; // graph's title y-coordinate
    public static final int LABEL_Y = 550; // all labels' y-coordinate
    public static final int START_X = 100; // each line starting point x-coordinate
    public static final int END_X = 580;// each line ending point x-coordinate
    public static final int TEXT_X = 50; // the x-coordinate of each number on the y axis
    public static final int MINVALUE_Y = 500; // the min value for vertical axis y-coordinate
    public static final int MAXVALUE_Y = 100; // the max value for vertical axis y-coordinate
    public static final int GAP_X = 70; // the gap between two labels in pixels


    /**
     * The actual main method that launches the app
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        stage.setTitle("Resolved Cases Program by Zhiping Yu");
        Image image = new Image("MohawkCollege.gif");
        gc.drawImage(image, 10, 10);

        Scanner sc = new Scanner(System.in); // create a scanner
        int horizontal = 100; // label's x-coordinate
        System.out.print("What is your graph title: ");
        String title = sc.nextLine();
        System.out.print("What is your minimum value for vertical axis: ");
        int min = sc.nextInt(); // minimum value for y-coordinate
        System.out.print("What is your maximum value for vertical axis: ");
        int max = sc.nextInt(); // max value for y-coordinate
        int difference = max - min; // range between max value and min value
        int eachGap = difference / 4; // divide range into 4 levels, the pixels between two closest level

        gc.setFill(PURPLE);
        gc.setFont(font("Courier New", SIZE_TITLE)); // set font
        gc.fillText(title, HEAD_X, HEAD_Y); // put graph title into a proper position
        gc.setFont(font("Courier New", SIZE_TEXT));
        // convert integer user entered into string and put it on the proper position in the graph
        gc.fillText(Integer.toString(min), TEXT_X, MINVALUE_Y);
        gc.strokeLine(START_X, MINVALUE_Y, END_X, MINVALUE_Y); // draw the bottom line at a stable position

        gc.fillText(Integer.toString((min + eachGap)), TEXT_X, MINVALUE_Y - 100);
        gc.strokeLine(START_X, MINVALUE_Y - 100, END_X, MINVALUE_Y - 100); // fourth line

        gc.fillText(Integer.toString(min+eachGap*2), TEXT_X, MINVALUE_Y - 200);
        gc.strokeLine(START_X, MINVALUE_Y - 200, END_X, MINVALUE_Y - 200); // third line

        gc.fillText(Integer.toString(min+eachGap*3), TEXT_X, MINVALUE_Y - 300);
        gc.strokeLine(START_X, MINVALUE_Y - 300, END_X, MINVALUE_Y - 300); // second line

        gc.fillText(Integer.toString(max), TEXT_X, MINVALUE_Y - 400);
        gc.strokeLine(START_X, MINVALUE_Y - 400, END_X, MINVALUE_Y - 400);// first line

        while (horizontal <= END_X) {
            Scanner sc1 = new Scanner(System.in); // create second scanner
            System.out.print("Enter a label or x for exit: ");
            String label = sc1.nextLine(); // get the label name from user
            if (label.equals("x")) break; // the condition for exit loop
            gc.fillText(label, horizontal, LABEL_Y);
            System.out.print("Enter a value between " + min + " and " + max + " :");
            int value = sc1.nextInt();

            while (true){ // use this loop to make sure numbers are in the range
                if (value < min || value > max) {
                    System.out.print("Value is illegal,enter a value between " + min + " and " + max + " :");
                    value = sc1.nextInt();
                }
                else break;
            }

            drawBarChart(gc, horizontal, value, min, max);
            horizontal += GAP_X; // update the value of label x-coordinate values when the loop continues
        }
        stage.show();

    }

    /*  This method uses the label name, vertical values , vertical max and min values to draw the bar chart.
     *  @param gc, used to draw stuff on the canvas
     *  @param horizontal, used to pinpoint each value between max and min x-coordinate
     *  @param value, user input
     *  @param min, max, used to make sure the range

     */
    private void drawBarChart(GraphicsContext gc,int  horizontal,int value, int min, int max){

        int range = MINVALUE_Y - MAXVALUE_Y;// calculate vertical range for the bar chart in pixels
        double h2 = ((double)(value-min)/(max-min))*range;// calculate the each value's height in the chart in pixels
        double barWidth = 20; // set the bar chart width for each bar in pixels
        double bar_y = MINVALUE_Y -h2; // calculate bars' starting point y-coordinate
        gc.fillRect(horizontal,bar_y,barWidth,h2); // draw the bars

    }
}







