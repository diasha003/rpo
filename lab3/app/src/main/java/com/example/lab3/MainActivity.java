package com.example.lab3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab3.showplaces.BelovezhskayaPushcha;
import com.example.lab3.showplaces.BrestFortress;
import com.example.lab3.showplaces.MirCastle;
import com.example.lab3.showplaces.MuzeiIstoriiVov;
import com.example.lab3.showplaces.NesvizhCastle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button dialog;
    private Context context;
    private ArrayList<Place> places;

    final String[] showplacesNamesArray = {"Mir Castle", "Belovezhskaya Pushcha National Park", "Nesvizh Castle", "Brest Fortress", "Museum of the History of the Great Patriotic War in Minsk"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog= (Button) findViewById(R.id.dialogButton);
        context = MainActivity.this;



        places = new ArrayList<>();
        places.add(new Place("MirCastle", "Mir Castle", "mir1", "Mir Castle - one of the most beautiful and famous castles in Belarus. It was built in the early 16th century and is a wonderful example of early Renaissance architecture.", "v1", "The Mir Castle is located in the town of Mir, Berezovsky district, Minsk region, Belarus.", "https://yandex.by/maps/?ll=26.473071%2C53.451232&z=13&l=map"));
        places.add(new Place("BelovezhskayaPushcha", "Belovezhskaya Pushcha", "pushcha", "Belovezhskaya Pushcha National Park - one of the last remaining places in Europe where you can see a European bison in its natural habitat. The park is also home to many other species of wildlife such as elk, deer, and lynx.", "belovezhskayapushcha", "National Park \"Belovezhskaya Pushcha\" is: Republic of Belarus, Brest region, Pruzhany district, Kamenets village.", "https://yandex.by/maps/106368/bialowieza/?ll=23.864742%2C52.712039&z=12.25"));
        places.add(new Place("NesvizhCastle", "Nesvizh Castle", "nesvizh", "Nesvizh Castle - another beautiful castle in Belarus, built in the 16th century. It is one of the largest castles in Eastern Europe and covers an area of over 2 hectares.", "nesvizhcastle", "The Nesvizh Castle is located at 28 Sovetskaya Street, Nesvizh 222603, Belarus.", "https://yandex.by/maps/26013/nesvizh/?ll=26.692006%2C53.222810&z=18"));
        places.add(new Place("BrestFortress", "Brest Fortress", "brestfortress", "Brest Fortress - a historical complex located in the city of Brest dedicated to the heroic defense of the fortress during the Great Patriotic War. The complex includes a museum and a memorial dedicated to the defenders of the fortress.", "brestfortress", "Brest Fortress is located at the address in Brest, Belarus: Gogolya St, 60, Brest 224030, Belarus", "https://yandex.by/maps/153/brest/?ll=23.657404%2C52.083087&z=17.13"));
        places.add(new Place("MuzeiIstoriiVov", "Museum of the History of the Great Patriotic War", "muzeiistoriivov", "One of the largest museums in Europe dedicated to World War II. It is a complex of dozens of halls where exhibits related to the history of the war and its consequences for Belarus and all of Europe are displayed.","muzeiistoriivov" , "The Museum of the Great Patriotic War is located at the address in Minsk, Belarus: Praspiekt Pieramožcaŭ 8, Minsk 220020, Belarus", "https://yandex.by/maps/153/brest/?ll=27.538440%2C53.916198&z=18"));



        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose showplaces F.S.");

                 builder.setItems(showplacesNamesArray, new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        Intent intent1 = new Intent(MainActivity.this, MirCastle.class);
                                        intent1.putParcelableArrayListExtra("places", places);
                                        startActivity(intent1);

                                        Toast.makeText(getApplicationContext(), "You have chosen Mir Castle",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        Intent intent2 = new Intent(MainActivity.this, BelovezhskayaPushcha.class);
                                        intent2.putParcelableArrayListExtra("places", places);
                                        startActivity(intent2);

                                        Toast.makeText(getApplicationContext(), "You have chosen Belovezhskaya Pushcha",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        Intent intent3 = new Intent(MainActivity.this, NesvizhCastle.class);
                                        intent3.putParcelableArrayListExtra("places", places);
                                        startActivity(intent3);

                                        Toast.makeText(getApplicationContext(), "You have chosen Belovezhskaya Pushcha",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 3:
                                        Intent intent4 = new Intent(MainActivity.this, BrestFortress.class);
                                        intent4.putParcelableArrayListExtra("places", places);
                                        startActivity(intent4);

                                        Toast.makeText(getApplicationContext(), "You have chosen Brest Fortress",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 4:
                                        Intent intent5 = new Intent(MainActivity.this, MuzeiIstoriiVov.class);
                                        intent5.putParcelableArrayListExtra("places", places);
                                        startActivity(intent5);

                                        Toast.makeText(getApplicationContext(), "You have chosen Museum of the History of the Great Patriotic War in Minsk",Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }
                );
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

}