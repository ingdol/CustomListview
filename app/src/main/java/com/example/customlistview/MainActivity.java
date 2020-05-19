package com.example.customlistview; //클래스를 상속받아 MainActivity를 작성한다.

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { //클래스를 상속받아 MainActivity를 작성한다.
    ListView list; //ListView를 list로 선언한다.
    String[] titles = { //title들을 문자열 배열로 작성한다.
            "The Wizard of Oz (1939)",
            "Citizen Kane (1941)",
            "All About Eve (1950)",
            "The Third Man (1949)",
            "A Hard Day's Night (1964)",
            "Modern Times (1936)",
            "Metropolis (1927)",
            "Metropolis (1927)",
            "Metropolis (1927)",
            "Metropolis (1927)"
    };
    Integer[] images = { //이미지를 가져와 정수 배열을 나열한다.
            R.drawable.movie1,
            R.drawable.movie2,
            R.drawable.movie3,
            R.drawable.movie4,
            R.drawable.movie5,
            R.drawable.movie6,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreat 메소드의 매개변수가 앱의 이전 실행 상태를 전달한다.
        super.onCreate(savedInstanceState); //부모클래스의 onCreate를 호출한다.
        setContentView(R.layout.activity_main); //메인을 띄운다.
        CustomList adapter = new CustomList(MainActivity.this); //CustomList 클래스 객체를 생성한다.
        list=(ListView)findViewById(R.id.list); //list는 레이아웃의 list 정보를 받는다.
        list.setAdapter(adapter); //커스텀 리스트에 어댑터를 연결한다.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //클릭하면 OnItemClickListenre 객체를 생성하여 실행한다.
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){ //아이템을 클릭하면 객체의 정보를 받는다.
                Toast.makeText(getBaseContext(), titles[+position],
                        Toast.LENGTH_SHORT).show(); //이를 토스트 메시지로 띄운다.
            }
        });
    }
    //내부 클래스로 정의한다.
    public class CustomList extends ArrayAdapter<String>{ //ArrayAdapter 클래스를 상속받아 CustomList를 작성한다.
        private final Activity context; //context는 초기화만 가능한 마지막 변수이다.
        public CustomList(Activity context){ //mainActivity에서 정보를 가져온다.
            super(context, R.layout.listitem, titles);
            //context와 listitem의 레이아웃 정보와 titles 정보를 부모클래스로 전달한다.
            this.context = context; //Context를 저장한다.
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) { //변환되는 뷰를 이용하여서 항목을 표시한다.
            LayoutInflater inflater = context.getLayoutInflater(); //사용할 레이아웃 정보를 가져와 바로 view로 생성한다.
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            //listitem에서 rowview를 하나씩 받아온다.
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image); //레이아웃 id에 맞게 view를 생성한다.
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rating = (TextView) rowView.findViewById(R.id.rating);
            TextView genre = (TextView) rowView.findViewById(R.id.genre);
            TextView year = (TextView) rowView.findViewById(R.id.releaseYear);
            title.setText(titles[position]); //position에 맞춰 titles을 세팅한다.
            imageView.setImageResource(images[position]); //position에 맞춰 이미지를 세팅한다.
            rating.setText("9.0"+position); //rating은 9.위치로 세팅한다.
            genre.setText("DRAMA"); //genre은 DRAMA로 입력한다.
            year.setText(1930+position+""); //year은 193위치로 세팅한다.
            return rowView; //rowView를 반환한다.
        }
    }
}
