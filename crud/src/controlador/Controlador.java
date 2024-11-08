package controlador;

import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.PersonaDAO;

public class Controlador implements ActionListener {

    PersonaDAO dao = new PersonaDAO();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Vista v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        listar(vista.tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tabla);
        } 
        if (e.getSource() == vista.btnGuardar) {
            agregar();
            limpiarTabla();
            listar(vista.tabla);
        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            } else {
                int id = Integer.parseInt(vista.tabla.getValueAt(fila, 0).toString());
                String nom = (String) vista.tabla.getValueAt(fila, 1);
                String correo = (String) vista.tabla.getValueAt(fila, 2);
                String tel = (String) vista.tabla.getValueAt(fila, 3);
                vista.txtId.setText(String.valueOf(id));
                vista.txtNombre.setText(nom);
                vista.txtCorreo.setText(correo);
                vista.txtTelefono.setText(tel);
            }
        }
        
        if (e.getSource() == vista.btnActualizar) {
            actualizar();
            limpiarTabla();
            listar(vista.tabla);
        }
        if (e.getSource() == vista.btnEliminar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un Usuario");
            } else {
                int id = Integer.parseInt(vista.tabla.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(vista, "Usuario Eliminado");
                limpiarTabla();
                listar(vista.tabla);
            }
        }
    }
    
    void limpiarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    
    public void actualizar() {
        int id = Integer.parseInt(vista.txtId.getText());
        String nom = vista.txtNombre.getText();
        String correo = vista.txtCorreo.getText();
        String tel = vista.txtTelefono.getText();

        Persona p = new Persona(); // Crear el objeto aquí
        p.setId(id);
        p.setNom(nom);
        p.setCorreo(correo);
        p.setTel(tel); // Asegúrate de establecer también el teléfono

        int r = dao.Actualizar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Actualizado con Éxito");
            listar(vista.tabla); // Actualizar la tabla después de la actualización
            limpiarCampos(); // Limpiar campos
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar el usuario");
        }
    }
    
    public void agregar() {
        String nom = vista.txtNombre.getText();
        String correo = vista.txtCorreo.getText();
        String tel = vista.txtTelefono.getText();

        Persona p = new Persona(); // Crear el objeto aquí
        p.setNom(nom);
        p.setCorreo(correo);  
        p.setTel(tel);

        int r = dao.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Agregado Con Éxito");
            listar(vista.tabla); // Actualizar la lista después de agregar
            limpiarCampos(); // Limpiar campos
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar usuario");
        }
    }

    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);  // Limpiar la tabla antes de listar
        List<Persona> lista = dao.listar();
        Object[] object = new Object[4];
        for (Persona persona : lista) {
            object[0] = persona.getId();
            object[1] = persona.getNom();
            object[2] = persona.getCorreo();
            object[3] = persona.getTel();
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtTelefono.setText("");
    }
}
