
public class confRoles {
	public static Rol configureRole(String nombreRol) {
        Rol role = new Rol(nombreRol);

        switch (nombreRol) {
            case "Cajero":
                role.agregarPermiso("VIEW_SALES");
                role.agregarPermiso("VIEW_INVENTORY");
                break;
            case "Farmacéutico":
                role.agregarPermiso("VIEW_INVENTORY");
                role.agregarPermiso("EDIT_PRESCRIPTIONS");
                role.agregarPermiso("VIEW_PRESCRIPTIONS");
                break;
            case "Asistente":
                role.agregarPermiso("VIEW_INVENTORY");
                role.agregarPermiso("EDIT_INVENTORY");
                break;
        }

        return role;
    }
}
