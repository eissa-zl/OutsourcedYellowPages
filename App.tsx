import { useRef } from 'react';
import {StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import {NativeModules} from 'react-native';

function App(): JSX.Element {


  async function init(){
    try {
      let res = await NativeModules.CommonModule.init();
      console.log(res);
    } catch (err) {
      console.log('Exception ', err);
    }
  }


  let contactData = {"addresses":[{"address":{"city":"Earleenmouth","country":"Cyprus","postalCode":"48027","state":"Montana","street":"63031 Niesha Pines"},"label":"Fnatic"}],"dob":"Jan 19, 2024 12:00:00 AM","emails":[{"email":"lillia.green@gmail.com","label":"Mallowpond Campus"}],"favourite":false,"firstName":"Johnie","id":"d7cdf91d-0cda-41c3-bcc3-7669597605c6","importantDates":[{"date":"Mon Sep 21 08:26:32 GMT+05:30 1964","label":"cedrick.bosco"}],"jobs":[{"company":"Bogan Inc","department":"Government","title":"Central Technology Planner"}],"lastName":"Raynor","phoneNumbers":[{"label":"Brookville Campus","phoneNumber":"(392) 383-7454 x12288"}],"profilePicture":"demo pp","sourceId":"default@mail.com","websites":["hahn.com","mraz.info","keebler.info"]}
  
  async function contactManager() {
    try {
      // let contact = JSON.parse(NativeModules.RandomDataGenerator.contact());
      // let res = await NativeModules.ContactManagerModule.create(contactData);
 

    
      // let res = await NativeModules.ContactManagerModule.get("0aad1efa-0453-466c-b9a2-46988ee882b7");
      // let res = await NativeModules.ContactManagerModule.getAll(10,0);
      
      // let res = await NativeModules.ContactManagerModule.getByGroup("4d880476-b14c-4c22-aec5-41aa7bdf34ce",10,0); //
      
      // let res = await NativeModules.ContactManagerModule.getFavourites(10,0); //

      // let res = await NativeModules.ContactManagerModule.search(contactData.firstName,10,0);

      // let res = await NativeModules.ContactManagerModule.getBySource([contactData.sourceId],10,0);
      
      // let res = await NativeModules.ContactManagerModule.delete(contactData.id);

      // let obj = JSON.parse(await NativeModules.ContactManagerModule.get("0aad1efa-0453-466c-b9a2-46988ee882b7"));
      // obj.lastName = "Hello"
      // let res = await NativeModules.ContactManagerModule.edit(obj);

      // let res = await NativeModules.ContactManagerModule.editFavourites(true, contactData.id);

      // let res = await NativeModules.ContactManagerModule.getCount();

      let res = await NativeModules.ContactManagerModule.getFavouriteCount();

      console.log(res);
    } catch (err) {
      console.log('Exception ', err);
    }
  }


  
  async function groupManager() {
    try {
      // let grp = {name:"Brookville College","profilePicture":"", id:"", contactIds:["d7cdf91d-0cda-41c3-bcc3-7669597605c6"]};

      let grp = JSON.parse(NativeModules.RandomDataGenerator.group());
      let res = await NativeModules.GroupManagerModule.create(grp);


      // grp.id = "4d880476-b14c-4c22-aec5-41aa7bdf34ce";
      // grp.name="Eissa";
      // let res = await NativeModules.GroupManagerModule.edit(grp);

      // let res = await NativeModules.GroupManagerModule.getAll(10,0); //here

      // let res = await NativeModules.GroupManagerModule.get(grp.id);

      // let res = await NativeModules.GroupManagerModule.delete(grp.id);

      // let res = await NativeModules.GroupManagerModule.getCount();

      // let res = await NativeModules.GroupManagerModule.searchGroup("Eissa", 10,0);

      console.log(res);
    } catch (err) {
      console.log('Exception ', err);
    }
  }

  async function sourceManager() {
    try {
      console.log(NativeModules.SourceManagerModule)
      let res = await NativeModules.SourceManagerModule.getSources();
      console.log(res);
    } catch (err) {
      console.log('Exception ', err);
    }
  }

  return (
    <View style={styles.pageContainer}>
      <TouchableOpacity
        style={styles.genericButton}
        onPress={() => {
          init();
        }}>
        <Text style={styles.text}>Initilize</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.genericButton}
        onPress={() => {
          sourceManager();
        }}>
        <Text style={styles.text}>SourceManager</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.genericButton}
        onPress={() => {
          groupManager();
        }}>
        <Text style={styles.text}>GroupManager</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.genericButton}
        onPress={() => {
          contactManager();
        }}>
        <Text style={styles.text}>ContactManager</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  pageContainer: {
    flex: 1,
    justifyContent: 'space-evenly',
  },
  text: {
    color: 'white',
  },
  genericButton: {
    alignSelf: 'center',
    padding: 10,
    backgroundColor: 'green',
    borderWidth: 2,
    borderRadius: 10,
  },
});

export default App;
