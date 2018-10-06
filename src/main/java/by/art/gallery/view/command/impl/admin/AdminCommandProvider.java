package by.art.gallery.view.command.impl.admin;

import by.art.gallery.view.command.impl.admin.impl.AdminArtObjects;
import by.art.gallery.view.command.impl.admin.impl.AdminAuthors;
import by.art.gallery.view.command.impl.admin.impl.AdminFormArts;
import by.art.gallery.view.command.impl.admin.impl.AdminMaterials;
import by.art.gallery.view.command.impl.admin.impl.AdminSubcontractors;
import by.art.gallery.view.command.impl.admin.impl.AdminPartners;
import by.art.gallery.view.command.impl.admin.impl.AdminUsers;
import by.art.gallery.view.command.impl.admin.impl.EditorArtObjects;
import by.art.gallery.view.command.impl.admin.impl.EditorAuthors;
import by.art.gallery.view.command.impl.admin.impl.EditorFormsArt;
import by.art.gallery.view.command.impl.admin.impl.EditorMaterials;
import by.art.gallery.view.command.impl.admin.impl.EditorPartners;
import by.art.gallery.view.command.impl.admin.impl.EditorSubcontractors;
import by.art.gallery.view.command.impl.admin.impl.EditorUsers;

import java.util.EnumMap;
import java.util.Map;

public class AdminCommandProvider {

    private Map<AdminCommandName, AdminCommand> commands = new EnumMap<>(AdminCommandName.class);

    public AdminCommandProvider() {
        commands.put(AdminCommandName.USERS, new AdminUsers());
        commands.put(AdminCommandName.AUTHORS, new AdminAuthors());
        commands.put(AdminCommandName.PARTNERS, new AdminPartners());
        commands.put(AdminCommandName.FORMS_ART, new AdminFormArts());
        commands.put(AdminCommandName.MATERIALS, new AdminMaterials());
        commands.put(AdminCommandName.ART_OBJECTS, new AdminArtObjects());
        commands.put(AdminCommandName.SUBCONTRACTORS, new AdminSubcontractors());
        commands.put(AdminCommandName.EDITOR_FORMS_ART, new EditorFormsArt());
        commands.put(AdminCommandName.EDITOR_MATERIALS, new EditorMaterials());
        commands.put(AdminCommandName.EDITOR_SUBCONTRACTORS, new EditorSubcontractors());
        commands.put(AdminCommandName.EDITOR_AUTHORS, new EditorAuthors());
        commands.put(AdminCommandName.EDITOR_ART_OBJECTS, new EditorArtObjects());
        commands.put(AdminCommandName.EDITOR_PARTNERS, new EditorPartners());
        commands.put(AdminCommandName.EDITOR_USERS, new EditorUsers());
    }

    public AdminCommand getCommand(String commandName) {
        AdminCommand command;
        command = commands.get(AdminCommandName.valueOf(commandName.toUpperCase()));
        return command;
    }
}
