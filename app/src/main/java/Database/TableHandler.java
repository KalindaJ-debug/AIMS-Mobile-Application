package Database;

import android.provider.BaseColumns;

public final class TableHandler {
    private TableHandler(){}

    protected static class Users implements BaseColumns {

        public static final String TABLE_NAME = "USER_TABLE";
        public static final String COL_1 = "USERNAME";
        public static final String COL_2 = "PASSWORD";
        public static final String COL_3 = "ROLE";

    }
}
