package com.one4ll.xplayer.interfaces;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.one4ll.xplayer.models.Music;
import com.one4ll.xplayer.models.Video;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MusicDao_Impl implements MusicDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Music> __insertionAdapterOfMusic;

  private final EntityDeletionOrUpdateAdapter<Music> __deletionAdapterOfMusic;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MusicDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMusic = new EntityInsertionAdapter<Music>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `music` (`_id`,`title`,`path`,`duration`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Music value) {
        stmt.bindLong(1, value.get_id());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getPath() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPath());
        }
        if (value.getDuration() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDuration());
        }
      }
    };
    this.__deletionAdapterOfMusic = new EntityDeletionOrUpdateAdapter<Music>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `music` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Music value) {
        stmt.bindLong(1, value.get_id());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE  FROM music";
        return _query;
      }
    };
  }

  @Override
  public void insertMusic(final Music image) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMusic.insert(image);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMusics(final List<Music> image) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMusic.insert(image);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteMusic(final Music image) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMusic.handle(image);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Video> getAll() {
    final String _sql = "SELECT * FROM music";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
      final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
      final List<Video> _result = new ArrayList<Video>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Video _item;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpPath;
        _tmpPath = _cursor.getString(_cursorIndexOfPath);
        final String _tmpDuration;
        _tmpDuration = _cursor.getString(_cursorIndexOfDuration);
        _item = new Video(null,_tmpTitle,_tmpPath,_tmpDuration);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
