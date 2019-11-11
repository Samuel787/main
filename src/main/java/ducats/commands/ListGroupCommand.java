package ducats.commands;

import ducats.DucatsException;
import ducats.Storage;
import ducats.Ui;
import ducats.components.Group;
import ducats.components.Song;
import ducats.components.SongList;

import java.util.ArrayList;

public class ListGroupCommand extends Command<SongList> {

    //@@author Samuel787

    private String message;

    public ListGroupCommand(String message){
        this.message = message.trim();
    }

    @Override
    public String execute(SongList songList, Ui ui, Storage storage) throws DucatsException {
        if(message.length() < 10 || !message.substring(0,10).equals("list_group")){
            throw new DucatsException(message, "list_group");
        } else if(songList.getSize() == 0){
            throw new DucatsException(message, "no_song_in_songlist");
        } else if (message.length() == 10){
            int songIndex = songList.getActiveIndex();
            Song activeSong = songList.getSongIndex(songIndex);
            ArrayList<Group> groups = activeSong.getGroups();
            return ui.formatListGroups(groups);
        } else {
            String keyword = message.substring(10).trim();
            int songIndex = songList.getActiveIndex();
            Song activeSong = songList.getSongIndex(songIndex);
            ArrayList<Group> resultGroups = getGroupsStartingWith(activeSong, keyword);
            return ui.formatListGroups(resultGroups);
        }
    }

    public ArrayList<Group> getGroupsStartingWith(Song song, String keyword){
        ArrayList<Group> allGroups = song.getGroups();
        ArrayList<Group> resultGroups =  new ArrayList<>();
        for(Group group : allGroups){
            if(group.getName().startsWith(keyword)){
                resultGroups.add(group);
            }
        }
        return resultGroups;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public int[] startMetronome() {
        return new int[]{-1, -1, -1, -1};
    }
}
