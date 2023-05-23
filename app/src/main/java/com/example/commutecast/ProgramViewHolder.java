package com.example.commutecast;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder {
    TextView programTitle;
    TextView programDescription;
    ProgramViewHolder(View v)
    {
        programTitle = v.findViewById(R.id.textView1);
        programDescription = v.findViewById(R.id.textView2);
    }
}
