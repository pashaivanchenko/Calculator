package calculator.db;

import calculator.ui.model.Transactions;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;
import com.db4o.ta.TransparentActivationSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DBQuery {
		private ObjectContainer db;
		private final File dbFile = new File("calc.db");
		private long id;
		public DBQuery() {
				openDB();
		}

		private void openDB() {
				EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
				conf.common().automaticShutDown(false);
				conf.common().optimizeNativeQueries(true);
				conf.common().add(new TransparentActivationSupport());
				db = Db4oEmbedded.openFile(conf, dbFile.getAbsolutePath());
				getId();
		}

		public void setTransaction(Transactions transaction){
				id++;
				db.store(new DBTransaction(id, transaction.getExpression(), transaction.getResult()));
				db.commit();
		}

		public List<Transactions> getTransactionsList(int col){
				Query query = db.query();
				query.sortBy(BY_ID);
				query.constrain(DBTransaction.class);
				ObjectSet<DBTransaction> objectSet = query.execute();
				List<Transactions> result = new ArrayList<>(col);
				for (DBTransaction dbTransaction : objectSet){
						result.add(new Transactions(dbTransaction.getExpression(), dbTransaction.getResult()));
				}
				return result;
		}

		private void getId(){
				ObjectSet<DBTransaction> objectSet = db.queryByExample(DBTransaction.class);
				if (objectSet == null) id = 0;
				else id = objectSet.size();
		}
		private static final Comparator<DBTransaction> BY_ID = new Comparator<DBTransaction>() {
				@Override
				public int compare(DBTransaction o1, DBTransaction o2) {
						return Long.compare(o1.getId(), o2.getId());
				}
		};

}
