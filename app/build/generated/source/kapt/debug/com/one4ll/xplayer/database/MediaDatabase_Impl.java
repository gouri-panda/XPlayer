package com.one4ll.xplayer.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.one4ll.xplayer.interfaces.FavoritesDao;
import com.one4ll.xplayer.interfaces.FavoritesDao_Impl;
import com.one4ll.xplayer.interfaces.ImageDao;
import com.one4ll.xplayer.interfaces.ImageDao_Impl;
import com.one4ll.xplayer.interfaces.MusicDao;
import com.one4ll.xplayer.interfaces.MusicDao_Impl;
import com.one4ll.xplayer.interfaces.StreamsDao;
import com.one4ll.xplayer.interfaces.StreamsDao_Impl;
import com.one4ll.xplayer.interfaces.VideoDao;
import com.one4ll.xplayer.interfaces.VideoDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MediaDatabase_Impl extends MediaDatabase {
  private volatile VideoDao _videoDao;

  private volatile ImageDao _imageDao;

  private volatile MusicDao _musicDao;

  private volatile StreamsDao _streamsDao;

  private volatile FavoritesDao _favoritesDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `video` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `title` TEXT NOT NULL, `path` TEXT NOT NULL, `duration` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `image` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `title` TEXT NOT NULL, `path` TEXT NOT NULL, `duration` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `music` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `path` TEXT NOT NULL, `duration` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `streams` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `path` TEXT NOT NULL, `time` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `favorite` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `path` TEXT NOT NULL, `time` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1bf295acdb7f6367c5efb5e9c53c9421')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `video`");
        _db.execSQL("DROP TABLE IF EXISTS `image`");
        _db.execSQL("DROP TABLE IF EXISTS `music`");
        _db.execSQL("DROP TABLE IF EXISTS `streams`");
        _db.execSQL("DROP TABLE IF EXISTS `favorite`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsVideo = new HashMap<String, TableInfo.Column>(4);
        _columnsVideo.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVideo.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVideo.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVideo.put("duration", new TableInfo.Column("duration", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVideo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVideo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVideo = new TableInfo("video", _columnsVideo, _foreignKeysVideo, _indicesVideo);
        final TableInfo _existingVideo = TableInfo.read(_db, "video");
        if (! _infoVideo.equals(_existingVideo)) {
          return new RoomOpenHelper.ValidationResult(false, "video(com.one4ll.xplayer.models.Video).\n"
                  + " Expected:\n" + _infoVideo + "\n"
                  + " Found:\n" + _existingVideo);
        }
        final HashMap<String, TableInfo.Column> _columnsImage = new HashMap<String, TableInfo.Column>(4);
        _columnsImage.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImage.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImage.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImage.put("duration", new TableInfo.Column("duration", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysImage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesImage = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoImage = new TableInfo("image", _columnsImage, _foreignKeysImage, _indicesImage);
        final TableInfo _existingImage = TableInfo.read(_db, "image");
        if (! _infoImage.equals(_existingImage)) {
          return new RoomOpenHelper.ValidationResult(false, "image(com.one4ll.xplayer.models.Image).\n"
                  + " Expected:\n" + _infoImage + "\n"
                  + " Found:\n" + _existingImage);
        }
        final HashMap<String, TableInfo.Column> _columnsMusic = new HashMap<String, TableInfo.Column>(4);
        _columnsMusic.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMusic.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMusic.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMusic.put("duration", new TableInfo.Column("duration", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMusic = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMusic = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMusic = new TableInfo("music", _columnsMusic, _foreignKeysMusic, _indicesMusic);
        final TableInfo _existingMusic = TableInfo.read(_db, "music");
        if (! _infoMusic.equals(_existingMusic)) {
          return new RoomOpenHelper.ValidationResult(false, "music(com.one4ll.xplayer.models.Music).\n"
                  + " Expected:\n" + _infoMusic + "\n"
                  + " Found:\n" + _existingMusic);
        }
        final HashMap<String, TableInfo.Column> _columnsStreams = new HashMap<String, TableInfo.Column>(3);
        _columnsStreams.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreams.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreams.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStreams = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStreams = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStreams = new TableInfo("streams", _columnsStreams, _foreignKeysStreams, _indicesStreams);
        final TableInfo _existingStreams = TableInfo.read(_db, "streams");
        if (! _infoStreams.equals(_existingStreams)) {
          return new RoomOpenHelper.ValidationResult(false, "streams(com.one4ll.xplayer.models.Streams).\n"
                  + " Expected:\n" + _infoStreams + "\n"
                  + " Found:\n" + _existingStreams);
        }
        final HashMap<String, TableInfo.Column> _columnsFavorite = new HashMap<String, TableInfo.Column>(3);
        _columnsFavorite.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavorite.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavorite.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavorite = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavorite = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavorite = new TableInfo("favorite", _columnsFavorite, _foreignKeysFavorite, _indicesFavorite);
        final TableInfo _existingFavorite = TableInfo.read(_db, "favorite");
        if (! _infoFavorite.equals(_existingFavorite)) {
          return new RoomOpenHelper.ValidationResult(false, "favorite(com.one4ll.xplayer.models.Favorite).\n"
                  + " Expected:\n" + _infoFavorite + "\n"
                  + " Found:\n" + _existingFavorite);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1bf295acdb7f6367c5efb5e9c53c9421", "94f324b760d618f7753dff0a9785862e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "video","image","music","streams","favorite");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `video`");
      _db.execSQL("DELETE FROM `image`");
      _db.execSQL("DELETE FROM `music`");
      _db.execSQL("DELETE FROM `streams`");
      _db.execSQL("DELETE FROM `favorite`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public VideoDao videoDao() {
    if (_videoDao != null) {
      return _videoDao;
    } else {
      synchronized(this) {
        if(_videoDao == null) {
          _videoDao = new VideoDao_Impl(this);
        }
        return _videoDao;
      }
    }
  }

  @Override
  public ImageDao imageDao() {
    if (_imageDao != null) {
      return _imageDao;
    } else {
      synchronized(this) {
        if(_imageDao == null) {
          _imageDao = new ImageDao_Impl(this);
        }
        return _imageDao;
      }
    }
  }

  @Override
  public MusicDao musicDao() {
    if (_musicDao != null) {
      return _musicDao;
    } else {
      synchronized(this) {
        if(_musicDao == null) {
          _musicDao = new MusicDao_Impl(this);
        }
        return _musicDao;
      }
    }
  }

  @Override
  public StreamsDao streamsDao() {
    if (_streamsDao != null) {
      return _streamsDao;
    } else {
      synchronized(this) {
        if(_streamsDao == null) {
          _streamsDao = new StreamsDao_Impl(this);
        }
        return _streamsDao;
      }
    }
  }

  @Override
  public FavoritesDao favoritesDao() {
    if (_favoritesDao != null) {
      return _favoritesDao;
    } else {
      synchronized(this) {
        if(_favoritesDao == null) {
          _favoritesDao = new FavoritesDao_Impl(this);
        }
        return _favoritesDao;
      }
    }
  }
}
