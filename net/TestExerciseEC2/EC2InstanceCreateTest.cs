using System;
using System.Collections.Generic;
using System.Linq;

using Amazon;
using Amazon.EC2;
using Amazon.EC2.Model;

using NUnit.Framework;

namespace TestExerciseEC2
{
    public class EC2InstanceCreateTest
    {
        static String keyName;
        static String secGroupName = "TestSecGroup";
        static AmazonEC2Client ec2client;

        List<Instance> instances;

        [OneTimeSetUp]
        public void OneTimeInit()
        {
            keyName = "key" + Guid.NewGuid().ToString();

            ec2client = new AmazonEC2Client(RegionEndpoint.APSoutheast2);

            CreateSecurityGroupRequest csgreq =
                    new CreateSecurityGroupRequest(
                        secGroupName,
                        "Test Security Group");

            CreateSecurityGroupResponse csgres =
                    ec2client.CreateSecurityGroup(csgreq);


            CreateKeyPairRequest createKeyPairRequest =
                    new CreateKeyPairRequest(keyName);

            CreateKeyPairResponse createKeyPairResult =
                    ec2client.CreateKeyPair(createKeyPairRequest);
        }

        [OneTimeTearDown]
        public void ClassTeardown()
        {
            DeleteKeyPairRequest dkpreq =
                    new DeleteKeyPairRequest(keyName);

            ec2client.DeleteKeyPair(dkpreq);

            DeleteSecurityGroupRequest dsgreq =
                    new DeleteSecurityGroupRequest(secGroupName);

            ec2client.DeleteSecurityGroup(dsgreq);
        }

        [SetUp]
        public void Init()
        {
            instances = null;
        }

        [TearDown]
        public void Teardown()
        {
            if (instances == null)
                return;

            TerminateInstancesRequest terminateRequest =
                    new TerminateInstancesRequest(
                        instances.Select(ins => ins.InstanceId).ToList());

            ec2client.TerminateInstances(terminateRequest);
        }

        [Test]
        public void Create_EC2_VM_Instance()
        {
            RunInstancesRequest runInstancesRequest =
                    new RunInstancesRequest()
                    {
                        ImageId = "ami-43874721",
                        InstanceType = "t2.micro",
                        MinCount = 1,
                        MaxCount = 1,
                        KeyName = keyName,
                        SecurityGroups = new List<string>() { secGroupName }
                    };

            RunInstancesResponse instanceRequestResult =
                    ec2client.RunInstances(runInstancesRequest);

            instances = instanceRequestResult.Reservation.Instances;

            Assert.That(instances.Count, Is.EqualTo(1));
        }
    }
}
