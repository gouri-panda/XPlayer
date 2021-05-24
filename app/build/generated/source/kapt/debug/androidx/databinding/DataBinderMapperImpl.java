package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.one4ll.xplayer.DataBinderMapperImpl());
  }
}
