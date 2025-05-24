package services;

import model.Atleta;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtletaServices {

    // Insertar atleta usando tu función crear_atleta
    public void insertarAtleta(Atleta atleta) throws Exception {
        String sql = "{ call crear_atleta(?, ?, ?, ?, ?, ?, ?) }";  
        
        try (DatabaseConnection dbConn = new DatabaseConnection();
             CallableStatement stmt = dbConn.getConnection().prepareCall(sql)) {
            
            stmt.setString(1, atleta.getIdAtleta());
            stmt.setString(2, atleta.getNomCompleto());
            stmt.setString(3, atleta.getSexo());
            stmt.setString(4, atleta.getCatDep());
            stmt.setString(5, atleta.getIdEntrenador());
            stmt.setString(6, atleta.getNomPais());
            stmt.setInt(7, atleta.getEdad());
            
            stmt.execute();
        } catch (SQLException e) {
            // Manejo específico de errores
            if (e.getSQLState().equals("23503")) { // Violación de FK
                throw new SQLException("Error: El país o entrenador no existe", e);
            } else if (e.getSQLState().equals("23514")) { // Violación de constraint
                throw new SQLException("Error: El atleta debe ser mayor de edad (18+ años)", e);
            }
            throw e;
        }
    }

    // Resto de métodos (actualizar, eliminar, buscar, listar) permanecen igual...
    // Solo asegúrate de que los nombres de funciones coincidan con los de tu BD
}