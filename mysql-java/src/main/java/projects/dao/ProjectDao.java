package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;
import provided.util.DaoBase;

public class ProjectDao extends DaoBase {

	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";
	private static final String STEP_TABLE = "step";

	public Project insertProject(Project project) {
		//@formatter:off 
		String sql = "" //this sql String gives the sql lanugage that will be used to communicate with the database 
		+ "INSERT INTO " + PROJECT_TABLE + "" 
		+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
		+ "VALUES"
		+ "(?, ?, ?, ?, ?)"; //? is a placeholder for sql 
		//@formatter:on

		try (Connection conn = DbConnection.getConnection()) {//a connection is established 
			startTransaction(conn); //the transaction is started

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				
				setParameter(stmt, 1, project.getProjectName(), String.class); //setParameter is a convenience method that allows us to set the various parameters 
				setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class);
				
				stmt.executeUpdate();
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				commitTransaction(conn);
				
				project.setProjectId(projectId);
				return project;
				
				
			} catch (Exception e) { //an exception is caught if it appears and the connection is rolledback before the exception is thrown to the user 
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

}
