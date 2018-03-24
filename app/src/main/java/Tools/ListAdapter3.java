package Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.paccount.Model.MDetail_Model;
import com.example.administrator.paccount.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class ListAdapter3 extends BaseAdapter {
    private TextView kindtextView;
    private TextView timetextView;
    private TextView moneytextView;
    private List<MDetail_Model.ListBean> list;
    private LayoutInflater layoutInflater;
    public ListAdapter3(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }
    public List<MDetail_Model.ListBean> getListItems() {
        return list;
    }

    public void setListItems(List<MDetail_Model.ListBean> listItems) {
        this.list = listItems;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            list=new ArrayList<MDetail_Model.ListBean>();
            convertView = layoutInflater.inflate(R.layout.detail_list,null);
            kindtextView=(TextView)convertView.findViewById(R.id.ttt_kind);
            timetextView=(TextView)convertView.findViewById(R.id.ttt_time);
            moneytextView=(TextView)convertView.findViewById(R.id.ttt_money);
            kindtextView.setText(list.get(position).getPaykind());
            timetextView.setText(list.get(position).getPaydate());
            moneytextView.setText(String.valueOf(list.get(position).getSubmoney()));
        }

        return convertView;
    }
}
