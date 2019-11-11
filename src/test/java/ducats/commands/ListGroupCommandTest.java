package ducats.commands;

import ducats.DucatsException;
import ducats.Ui;
import ducats.components.SongConverter;
import ducats.components.SongList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListGroupCommandTest {
    //@@author Samuel787

    @Test
    public void testListGroupCommand_validInput_success() {
        String fileDelimiter = System.getProperty("file.separator");
        SongConverter songconverter = new SongConverter();
        ducats.Storage storage = new ducats.Storage(System.getProperty("user.dir") + fileDelimiter + "songlist.txt");

        SongList songs = new SongList();
        String testSong = "Jingle aminor 120 [[UAs],[UA],[UCs],[UC],[LBs],[LB],[LAs],[LA]] "
                + "[[UFs],[UF],[UEs]," + "[UE]," + "[UDs],[UD],[LGs],[LG]] "
                + "[[MCs],[MC],[MC],[MC],[LCs],[LC],[LC],[LC]]";
        try {
            songs.add(songconverter.convertSongFromString(testSong));
        } catch (Exception e) {
            System.out.println(testSong);
            songs = new SongList();
        }

        Ui ui = new Ui();
        ducats.commands.OpenCommand open = new ducats.commands.OpenCommand("open Jingle");
        try {
            open.execute(songs, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        GroupCommand groupCommand = new GroupCommand("group 1 2 test");
        try {
            groupCommand.execute(songs, ui, storage);
        } catch (Exception e) {
            System.out.println(e);
        }

        GroupCommand groupCommand2 = new GroupCommand("group 1 3 hello");
        try {
            groupCommand2.execute(songs, ui, storage);
        } catch (Exception e) {
            System.out.println(e);
        }

        GroupCommand groupCommand3 = new GroupCommand("group 1 1 mellow");
        try {
            groupCommand3.execute(songs, ui, storage);
        } catch (Exception e) {
            System.out.println(e);
        }

        ListGroupCommand listGroupCommand = new ListGroupCommand("list_group");
        String result = "";
        try {
            result = listGroupCommand.execute(songs, ui, storage);
        } catch (DucatsException e) {
            System.out.println(e);
        }
        String expected = "Here are the groups available: \n"
                + "1. test\n" + "2. hello\n" + "3. mellow\n";

        assertEquals(expected, result);
    }

    @Test
    public void testListGroupCommand_startingStringInput_success() {
        String fileDelimiter = System.getProperty("file.separator");
        SongConverter songconverter = new SongConverter();
        ducats.Storage storage = new ducats.Storage(System.getProperty("user.dir")
                + fileDelimiter + "songlist.txt");

        SongList songs = new SongList();
        String testSong =
                "Jingle aminor 120 [[UAs],[UA],[UCs],[UC],[LBs],[LB],[LAs],[LA]] "
                        + "[[UFs],[UF],[UEs]," + "[UE],"
                        + "[UDs],[UD],[LGs],[LG]] "
                        + "[[MCs],[MC],[MC],[MC],[LCs],[LC],[LC],[LC]]";
        try {
            songs.add(songconverter.convertSongFromString(testSong));
        } catch (Exception e) {
            System.out.println(testSong);
            songs = new SongList();
        }

        Ui ui = new Ui();
        ducats.commands.OpenCommand open = new ducats.commands.OpenCommand("open Jingle");
        try {
            open.execute(songs, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        GroupCommand groupCommand = new GroupCommand("group 1 2 hell");
        try {
            groupCommand.execute(songs, ui, storage);
        } catch (Exception e) {
            System.out.println(e);
        }

        GroupCommand groupCommand2 = new GroupCommand("group 1 3 hello");
        try {
            groupCommand2.execute(songs, ui, storage);
        } catch (Exception e) {
            System.out.println(e);
        }

        GroupCommand groupCommand3 = new GroupCommand("group 1 1 mellow");
        try {
            groupCommand3.execute(songs, ui, storage);
        } catch (Exception e) {
            System.out.println(e);
        }

        ListGroupCommand listGroupCommand = new ListGroupCommand("list_group hel");
        String result = "";
        try {
            result = listGroupCommand.execute(songs, ui, storage);
        } catch (DucatsException e) {
            System.out.println(e);
        }
        String expected = "Here are the groups available: \n" + "1. hell\n" + "2. hello\n";

        assertEquals(expected, result);
    }

    @Test
    public void testListGroupCommand_noSongInStorage_noSongInSonglistException() {
        String fileDelimiter = System.getProperty("file.separator");
        ducats.Storage storage = new ducats.Storage(System.getProperty("user.dir")
                + fileDelimiter + "songlist.txt");

        SongList songs = new SongList();

        Ui ui = new Ui();

        ListGroupCommand listGroupCommand = new ListGroupCommand("list_group hel");
        String result = "";
        try {
            result = listGroupCommand.execute(songs, ui, storage);
        } catch (DucatsException e) {
            System.out.println(e);
            assertEquals("no_song_in_songlist", e.getType());
            return;
        }
        assert false;
        String expected = "";
        assertEquals(expected, result);
    }

    //@@author rohan-av
    @Test
    void testStartMetronome() {
        ListGroupCommand listGroupCommand = new ListGroupCommand("placeholder");
        assertArrayEquals(new int[]{-1, -1, -1, -1}, listGroupCommand.startMetronome());
    }

    @Test
    void testIsExit() {
        ListGroupCommand listGroupCommand = new ListGroupCommand("placeholder");
        assertFalse(listGroupCommand.isExit());
    }
}
