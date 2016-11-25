package Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.faseapp.faseapp.CardPay;
import com.faseapp.faseapp.R;

import Utils.CitrusPay;
import Utils.MyDebugClass;

/**
 * Created by Sonu on 21-Oct-16.
 */
public class TransferAndRefill_Fragment extends Fragment {
    CitrusPay citrusPay;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_transfer_n_refill,container,false);
        LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.refill);
        citrusPay=new CitrusPay(getContext());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogForMode();

               // startActivity(new Intent(getContext(), CardPay.class));
            }
        });
        return view;
    }

    public void alertDialogForMode(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choose)
                .setItems(R.array.LoadMoneyOption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                alertDialogForBankName();
                            case 1:
                               startActivity(new Intent(getContext(),CardPay.class).putExtra("cardType","debitCard"));
                            case 2:
                              startActivity(new Intent(getContext(),CardPay.class).putExtra("cardType","creditCard"));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                         MyDebugClass.showToast(getContext(),"Please choose a mode to load balance");        // User cancelled the dialog
                    }
                });
        builder.create();

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void alertDialogForBankName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choose)
                .setItems(R.array.bankList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       citrusPay.loadMoneyFromNetBanking(getContext().getResources().getStringArray(R.array.bankList)[which],getContext().getResources().getStringArray(R.array.bankCID)[which],"10");
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MyDebugClass.showToast(getContext(),"Please choose a mode to load balance");        // User cancelled the dialog
                    }
                });
        builder.create();

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void enterAmount(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choose)
                .setItems(R.array.LoadMoneyOption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                startActivity(new Intent(getContext(), CardPay.class));
                            case 1:
                                // startActivity(new Intent(getContext(),CardPay.class).putExtra("cardType","debitCard"));
                            case 2:
                                // startActivity(new Intent(getContext(),CardPay.class).putExtra("cardType","creditCard"));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MyDebugClass.showToast(getContext(),"Please choose a mode to load balance");        // User cancelled the dialog
                    }
                });
        builder.create();

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
