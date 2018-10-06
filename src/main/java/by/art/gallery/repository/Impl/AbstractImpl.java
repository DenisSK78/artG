package by.art.gallery.repository.Impl;

import by.art.gallery.repository.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class AbstractImpl<E>{

    E createResult(ResultSet resultSet) throws SQLException, DAOException {
        return resultSet.next() ? getGalObject(resultSet) : null;
    }

    List<E> createResultList(ResultSet resultSet) throws SQLException, DAOException {
        if (resultSet.isBeforeFirst()){
            List<E> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(getGalObject(resultSet));
            }
            return list;
        }
        return Collections.emptyList();
    }

    abstract E getGalObject(ResultSet resultSet) throws SQLException, DAOException;

    abstract void setStatement(PreparedStatement statement, E e) throws SQLException;
}
