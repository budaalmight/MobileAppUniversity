package user.mobileappuni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class OurDialog extends Activity {
    @Override
    public void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.dialog_layout);
        int visits = save.getInt("visits");
        ImageButton close = (ImageButton) findViewById(R.id.dialogExitIconButton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView discount = (TextView) findViewById(R.id.dialogDiscountLabel);
        if (visits == 10) {
            discount.setText("10% DISCOUNT FROM YOUR NEXT ORDER");
        } else if (visits == 15) {
            discount.setText("15% DISCOUNT FROM YOUR NEXT ORDER");
        } else discount.setVisibility(View.INVISIBLE);
    }
}
