package com.thoughtworks.trackmanagement;

import java.io.IOException;
import java.util.List;

import com.thoughtworks.trackmanagement.exception.InvalidTalkSpecsException;
import com.thoughtworks.trackmanagement.model.Talk;
import com.thoughtworks.trackmanagement.planner.ConferencePlanner;
import com.thoughtworks.trackmanagement.serialization.ConferenceSerializer;
import com.thoughtworks.trackmanagement.utils.ConferenceFileReader;
import com.thoughtworks.trackmanagement.utils.TalkParser;

/**
 * App for scheduling talks during a multi track conference.
 *
 */
public class ConferenceTrackManagement 
{
    public static void main( String[] args )
    {
    	try{
    		ConferenceFileReader reader = new ConferenceFileReader();
    		ConferencePlanner planner = new ConferencePlanner();
    		ConferenceSerializer serializer = new ConferenceSerializer();
    		
    		List<String> lines = reader.readFile(args[0]);
    		List<Talk> talks = TalkParser.parseList(lines);
    		planner.buildSessions(talks);
    		
    		System.out.println(serializer.serialize(planner.getSessions()));
    		System.out.println("Lost total time: " + planner.getWasted() + " minutes");
    	}catch(IOException e){
    		System.out.println("Error reading the file");
    		e.printStackTrace();
    	} catch (InvalidTalkSpecsException e) {
    		System.out.println("Error int the specifications of the talks");
			e.printStackTrace();
		}
    }
}
