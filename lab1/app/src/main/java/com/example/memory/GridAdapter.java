package com.example.memory;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class GridAdapter extends BaseAdapter {

    /*private static final int CALL_CLOSE = 0;
    private static final int CALL_OPEN = 1;
    private static final int CALL_DELETE = -1;*/

    public static enum Status {CELL_OPEN, CELL_CLOSE, CELL_DELETE};
    private ArrayList<Status> arrStatus; //состояние ячеек

    private ArrayList<String> nameThreeCard;

    private Context mContext;
    private Integer mCols, mRows;

    private ArrayList<String> arrPict; //массив картинок
    private String PictureCollection; //префикс набора картинок
    private Resources mRes; //ресурсы приложения

    public GridAdapter(Context context, Integer mCols, Integer mRows){
        this.mContext = context;
        this.mCols = mCols;
        this.mRows = mRows;


        arrPict = new ArrayList<String>();
        arrStatus = new ArrayList<Status>();
        nameThreeCard = new ArrayList<String>();
        PictureCollection = "card";
        mRes = mContext.getResources();


        makePictArray();
        closeAllCells(); //устанавливает всем ячейкам первоначальный статус
    }

    public void closeAllCells(){
        arrStatus.clear();
        for (int i=0 ; i < (mCols*mRows); i++){
            arrStatus.add(Status.CELL_CLOSE);
        }
    }

    public void makePictArray() {
        arrPict.clear();
        int countThree = (int) Math.ceil((Math.ceil(mRows*mCols/3))/3);
        //System.out.println(countThree);
        ArrayList<Integer> randomNum = new ArrayList<Integer>();

       for (int i=0; i< countThree; i++){
            randomNum.add(ThreadLocalRandom.current().nextInt(10, 21));
        }


       for(int i=0; i<randomNum.size(); i++){
           nameThreeCard.add(PictureCollection + Integer.toString(randomNum.get(i)));
           arrPict.add(PictureCollection + Integer.toString(randomNum.get(i)));
           arrPict.add(PictureCollection + Integer.toString(randomNum.get(i)));
           arrPict.add(PictureCollection + Integer.toString(randomNum.get(i)));
       }

        for (int i=0; i < ((mRows*mCols-countThree*3)/2) ; i++){
            arrPict.add(PictureCollection + Integer.toString(i));
            arrPict.add(PictureCollection + Integer.toString(i));
            //System.out.println(PictureCollection + Integer.toString(i));
        }

        Collections.shuffle(arrPict); //перемешивает массив
    }

    public void checkOpenCells() {

        int first = arrStatus.indexOf(Status.CELL_OPEN);
        int second = arrStatus.lastIndexOf(Status.CELL_OPEN);

        if (first == second)
            return;



        if (arrPict.get(first).equals(arrPict.get(second))) {

            ArrayList<Integer> number = new ArrayList<Integer>();

            for(int i=0; i< arrStatus.size(); i++){
                if (arrStatus.get(i).equals(Status.CELL_OPEN)){
                    number.add(i);
                }
            }

            if (nameThreeCard.contains(arrPict.get(first))){
                if (number.size() == 3){
                   if(arrPict.get(number.get(0)).equals(arrPict.get(number.get(1))) &&
                           arrPict.get(number.get(1)).equals(arrPict.get(number.get(2)))){
                       arrStatus.set(number.get(0), Status.CELL_DELETE);
                       arrStatus.set(number.get(1), Status.CELL_DELETE);
                       arrStatus.set(number.get(2), Status.CELL_DELETE);
                   } else{
                       arrStatus.set(number.get(0), Status.CELL_CLOSE);
                       arrStatus.set(number.get(1), Status.CELL_CLOSE);
                       arrStatus.set(number.get(2), Status.CELL_CLOSE);
                   }

                } else{

                    return;
                }

            } else {
                arrStatus.set(first, Status.CELL_DELETE);
                arrStatus.set(second, Status.CELL_DELETE);
            }


        } else {

            for(int i=0; i< arrStatus.size(); i++){
                if (arrStatus.get(i).equals(Status.CELL_OPEN)) arrStatus.set(i, Status.CELL_CLOSE);
            }

            //arrStatus.set(first, Status.CELL_CLOSE);
            //arrStatus.set(second, Status.CELL_CLOSE);

        }
        return;
    }





    public boolean openCell(int position){


        if (arrStatus.get(position) == Status.CELL_DELETE ||
                arrStatus.get(position) == Status.CELL_OPEN) {
            return false;
        }

        if (arrStatus.get(position) != Status.CELL_DELETE)
            arrStatus.set(position, Status.CELL_OPEN);

        notifyDataSetChanged(); //синхронизировать изменения с адаптером
        return true;

    }

    public boolean checkGameOver(){
        if (arrStatus.indexOf(Status.CELL_CLOSE) < 0)
            return true;
        return false;
    }


    @Override
    public int getCount() { //кол-во элементов
        return mCols * mRows;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) { //- возвращает View, который будет выведен в ячейке под номером i

        ImageView view; // для вывода картинки

        if (convertView == null)
            view = new ImageView(mContext);
        else
            view = (ImageView) convertView;


        switch (arrStatus.get(i)){
            case CELL_OPEN:
                Integer drawableId = mRes.getIdentifier(arrPict.get(i), "drawable", mContext.getPackageName());
                view.setImageResource(drawableId);
                break;
            case CELL_CLOSE:
                //Integer drawable = mRes.getIdentifier(arrPict.get(i), "drawable", mContext.getPackageName());
                //view.setImageResource(drawable);
                view.setImageResource(R.drawable.back);
                break;
            //case CELL_DELETE:
                //view.setImageResource(R.mipmap.ic_launcher);

            default:
                view.setImageResource(R.drawable.x_button);
        }


        //view.setImageResource(R.mipmap.ic_launcher);


        return view;

    }
}
