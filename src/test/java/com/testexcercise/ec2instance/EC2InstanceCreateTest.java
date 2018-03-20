package com.testexcercise.ec2instance;

import java.util.*;
import java.util.stream.*;

import com.amazonaws.regions.*;
import com.amazonaws.services.ec2.*;
import com.amazonaws.services.ec2.model.*;

import org.testng.*;
import org.testng.annotations.*;

public class EC2InstanceCreateTest
{
    static String keyName;
    static String secGroupName = "TestSecGroup";
    static AmazonEC2 ec2client;
    
    List<Instance> instances;
    
    @BeforeClass
    public void classSetup()
    {
        keyName = "key"+UUID.randomUUID().toString();
        
        AmazonEC2ClientBuilder clientBuilder =
                AmazonEC2ClientBuilder.
                    standard().
                    withRegion(Regions.AP_SOUTHEAST_2);
        
        ec2client = clientBuilder.build();
        
        CreateSecurityGroupRequest csgreq = 
                new CreateSecurityGroupRequest().
                    withGroupName(secGroupName).
                    withDescription("Test Security Group");
        
        CreateSecurityGroupResult csgres =
                ec2client.createSecurityGroup(csgreq);
        
        
        CreateKeyPairRequest createKeyPairRequest =
                new CreateKeyPairRequest().
                    withKeyName(keyName);
        
        CreateKeyPairResult createKeyPairResult =
                ec2client.createKeyPair(createKeyPairRequest);
    }
    
    @AfterClass
    public void classTeardown()
    {
        DeleteKeyPairRequest dkpreq = 
                new DeleteKeyPairRequest().
                    withKeyName(keyName);
        ec2client.deleteKeyPair(dkpreq);
        
        DeleteSecurityGroupRequest dsgreq =
                new DeleteSecurityGroupRequest().
                    withGroupName(secGroupName);
        
        ec2client.deleteSecurityGroup(dsgreq);
    }
    
    @BeforeMethod
    public void setup()
    {
        instances = null;
    }
    
    @AfterMethod
    public void teardown()
    {
        if (instances == null)
            return;
        
        TerminateInstancesRequest terminateRequest = 
                new TerminateInstancesRequest().
                    withInstanceIds(
                            instances.stream().
                            map(x -> x.getInstanceId()).
                            collect(Collectors.toList()));
        
        ec2client.terminateInstances(terminateRequest);
    }
    
    @Parameters({ "imageId", "instanceType" })
    @Test
    public void createEC2VMInstance(
            String imageId,
            String instanceType)
    {   
        RunInstancesRequest runInstancesRequest =
                new RunInstancesRequest().
                    withImageId("ami-43874721").
                    withInstanceType("t2.micro").
                    withMinCount(1).
                    withMaxCount(1).
                    withKeyName(keyName).
                    withSecurityGroups(secGroupName);
        
        RunInstancesResult instanceRequestResult =
                ec2client.runInstances(runInstancesRequest);
        
        instances = instanceRequestResult.
                getReservation().
                getInstances();
        
        Assert.assertEquals(instances.size(), 1);
    }    
}
