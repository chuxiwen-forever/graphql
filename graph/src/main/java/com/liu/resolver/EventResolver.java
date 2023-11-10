package com.liu.resolver;

import com.liu.DO.EventDO;
import com.liu.entity.Event;
import com.liu.param.EventInput;
import com.liu.util.DateUtil;
import com.liu.vo.EventVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(
        componentModel = "spring"
)
public interface EventResolver {

    @Mapping(target = "date", qualifiedByName = "stringToDate")
    Event toEvent(EventInput input);

    @Mapping(target = "date", qualifiedByName = "dateToString")
    EventVO toEventVO(Event event);

    @Named("stringToDate")
    default Date stringToDate(String date) {
        return DateUtil.convertISOStringToDate(date);
    }

    @Named("dateToString")
    default String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
