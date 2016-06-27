package user.mobileappuni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class OurDialog extends Activity {
    @Override
    public void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.dialog_layout);
        Intent intent = getIntent();
        int visits = intent.getIntExtra("visits",1);
        ImageButton close = (ImageButton) findViewById(R.id.dialogExitIconButton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView text = (TextView) findViewById(R.id.dialogVisitText);
        text.setText("This is your "+visits+"th visit in our sport club.");
        TextView discount = (TextView) findViewById(R.id.dialogDiscountLabel);
        if (visits == 10) {
            text.setText("This is your "+visits+"th visit in our sport club.That is why you get:");
            discount.setText("10% DISCOUNT FROM YOUR NEXT ORDER");
        } else if (visits == 15) {
            text.setText("This is your "+visits+"th visit in our sport club.That is why you get:");
            discount.setText("15% DISCOUNT FROM YOUR NEXT ORDER");
        } else discount.setVisibility(View.INVISIBLE);
    }
}
