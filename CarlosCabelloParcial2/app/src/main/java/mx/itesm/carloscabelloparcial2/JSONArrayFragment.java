package mx.itesm.carloscabelloparcial2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONArrayFragment extends Fragment implements RetrieveJSONTask.RequestListener{
    private Context context;
    TextView tv1;
    TextView tv3;
    TextView tv4;
    private RequestListener listener;


    public JSONArrayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jsonarray, container, false);

        tv1 = v.findViewById(R.id.textView);
        tv3 = v.findViewById(R.id.textView3);
        tv4 = v.findViewById(R.id.textView4);
        Button b = v.findViewById(R.id.button);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "FROM THE FRAGMENT", Toast.LENGTH_SHORT).show();
            }
        });

        doRequest(v);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void doRequest(View v) {

        RetrieveJSONTask task = new RetrieveJSONTask(this);
        task.execute("http://api.myjson.com/bins/1flpdc");
    }

    @Override
    public void requestDone(JSONArray jsonArray) {

        try {
            //Toast.makeText(this, jsonObject.getString("current_user_url"), Toast.LENGTH_SHORT).show();

            /*for(int i = 0; i < jsonArray.length(); i++){

                JSONObject current = jsonArray.getJSONObject(i);
                Log.wtf("JSONTEST", "BRAND: " + current.getString("brand") + " MODEL: " + current.getString("model") + " YEAR: " + current.getInt("year"));
            }*/
            tv1.setText(jsonArray.getJSONObject(0).getString("brand"));
            tv3.setText(jsonArray.getJSONObject(0).getString("model"));
            tv4.setText(jsonArray.getJSONObject(0).getInt("year") + "");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface RequestListener{

        void requestDone(String text);
    }

}

