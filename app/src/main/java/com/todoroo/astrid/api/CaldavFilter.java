package com.todoroo.astrid.api;

import android.os.Parcel;

import com.todoroo.andlib.sql.Criterion;
import com.todoroo.andlib.sql.Field;
import com.todoroo.andlib.sql.Join;
import com.todoroo.andlib.sql.QueryTemplate;
import com.todoroo.astrid.dao.TaskDao;
import com.todoroo.astrid.data.Task;

import org.tasks.R;
import org.tasks.data.CaldavTask;
import org.tasks.data.CaldavAccount;

import java.util.HashMap;
import java.util.Map;

public class CaldavFilter extends Filter {

    private static final int TAG = R.drawable.ic_cloud_black_24dp;

    private CaldavAccount account;

    private CaldavFilter() {
        super();
    }

    public CaldavFilter(CaldavAccount account) {
        super(account.getName(), queryTemplate(account), getValuesForNewTask(account));
        this.account = account;
        tint = account.getColor();
        icon = TAG;
    }

    public String getUuid() {
        return account.getUuid();
    }

    private static QueryTemplate queryTemplate(CaldavAccount caldavAccount) {
        return new QueryTemplate()
                .join(Join.left(CaldavTask.TABLE, Task.ID.eq(Field.field("caldav_tasks.task"))))
                .where(Criterion.and(
                        TaskDao.TaskCriteria.activeAndVisible(),
                        Field.field("account").eq(caldavAccount.getUuid())));
    }

    private static Map<String, Object> getValuesForNewTask(CaldavAccount caldavAccount) {
        Map<String, Object> result = new HashMap<>();
        result.put(CaldavTask.KEY, caldavAccount.getUuid());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(account, 0);
    }

    @Override
    protected void readFromParcel(Parcel source) {
        super.readFromParcel(source);
        account = source.readParcelable(getClass().getClassLoader());
    }

    /**
     * Parcelable Creator Object
     */
    public static final Creator<CaldavFilter> CREATOR = new Creator<CaldavFilter>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public CaldavFilter createFromParcel(Parcel source) {
            CaldavFilter item = new CaldavFilter();
            item.readFromParcel(source);
            return item;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public CaldavFilter[] newArray(int size) {
            return new CaldavFilter[size];
        }

    };

    @Override
    public boolean supportsSubtasks() {
        return true;
    }
}
