package com.alonz.reumanatlot;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.alonz.reumanatlot.Natlot.Natla;

import java.util.List;

/**
 * Created by alonz on 08/12/2017.
 */
@Dao
public interface NatlaDao {

    @Query("SELECT * FROM Natla")
    List<Natla> getAll();

    @Query("SELECT * FROM Natla WHERE id = :id")
    Natla getNoteById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Natla... natla);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Natla natla);

    @Delete
    void delete(Natla natla);

    @Query("DELETE FROM Natla")
    void deleteAll();




}
