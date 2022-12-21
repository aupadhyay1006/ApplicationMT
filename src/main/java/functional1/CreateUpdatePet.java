package functional1;

import base.FunctionalBaseTest;
import client.PetStoreTestClass;
import com.google.common.collect.Lists;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import request.Category;
import request.Pet;
import request.PetStatus;
import request.Tag;
import util.TestHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CreateUpdatePet extends FunctionalBaseTest {
    private static String updatedPetName ;
    private static Long updatedPetId ;
    private static PetStatus updatedPetStatus ;

    @Override
    public void setupData() throws Exception {
    }

    @DataProvider
    public Iterator<Object[]> getCreatePetData(){
        List<Object[]> data = Lists.newLinkedList();

        SortedMap<String,String> sortedMapDog = new TreeMap<>();
        sortedMapDog.put("1","https://pixabay.com/photos/puppy-pet-canine-dog-animal-lying-2785074");
        sortedMapDog.put("2","https://www.shutterstock.com/image-photo/puppy-dog-golden-retriever-on-white-1079672630");
        sortedMapDog.put("3","https://pixabay.com/photos/puppy-dog-pet-collar-dog-collar-1903313");
        List<String>  photoUrlsDog = new ArrayList<>();
        for(Map.Entry<String,String> entry : sortedMapDog.entrySet()){
            photoUrlsDog.add(String.valueOf(entry.getValue()));
        }

        SortedMap<String,String> sortedMapCat = new TreeMap<String,String>();
        sortedMapCat.put("1","\"https://www.pexels.com/photo/close-up-photo-of-cute-sleeping-cat-416160/");
        sortedMapCat.put("2","\"https://www.pexels.com/photo/white-and-grey-kitten-on-brown-and-black-leopard-print-textile-45201/");
        List<String>  photoUrlsCat = new ArrayList<>();
        for(Map.Entry<String,String> entry : sortedMapCat.entrySet()){
            photoUrlsCat.add(String.valueOf(entry.getValue()));
        }

        SortedMap<String,String> sortedMapCow = new TreeMap<String,String>();
        sortedMapCow.put("1","\"https://www.pexels.com/photo/brown-calf-inside-barn-1449656/");
        List<String>  photoUrlsCow = new ArrayList<>();
        for(Map.Entry<String,String> entry : sortedMapCow.entrySet()){
            photoUrlsCow.add(String.valueOf(entry.getValue()));
        }

        Category dogCategory = Category.builder()
                .id(10)
                .name("dogCategory")
                .build();

        Category catCategory = Category.builder()
                .id(20)
                .name("catCategory")
                .build();

        Category cowCategory = Category.builder()
                .id(30)
                .name("cowCategory")
                .build();

        Tag dogTag1 =Tag.builder()
                .id(1)
                .name("dogTag1")
                .build();

        Tag dogTag2 =Tag.builder()
                .id(2)
                .name("dogTag2")
                .build();

        Tag catTag1 =Tag.builder()
                .id(3)
                .name("catTag1")
                .build();

        Tag catTag2 =Tag.builder()
                .id(4)
                .name("catTag2")
                .build();

        Tag cowTag1 =Tag.builder()
                .id(5)
                .name("cowTag1")
                .build();

        Tag cowTag2 =Tag.builder()
                .id(6)
                .name("cowTag2")
                .build();

        Tag cowTag3 =Tag.builder()
                .id(7)
                .name("cowTag3")
                .build();

        List<Tag> dogTagsList = new ArrayList<Tag>();
        dogTagsList.add(dogTag1);
        dogTagsList.add(dogTag2);

        List<Tag> catTagsList = new ArrayList<Tag>();
        catTagsList.add(catTag1);
        catTagsList.add(catTag2);

        List<Tag> cowTagsList = new ArrayList<Tag>();
        cowTagsList.add(cowTag1);
        cowTagsList.add(cowTag2);
        cowTagsList.add(cowTag3);

        Pet pet1 = Pet.builder()
                .id(100)
                .category(dogCategory)
                .name("dog")
                .photoUrls(photoUrlsDog)
                .tags(dogTagsList)
                .status(PetStatus.available)
                .build();

        Pet pet2 = Pet.builder()
                .id(200)
                .category(catCategory)
                .name("cat")
                .photoUrls(photoUrlsCat)
                .tags(catTagsList)
                .status(PetStatus.sold)
                .build();

        Pet pet3 = Pet.builder()
                .id(300)
                .category(cowCategory)
                .name("cow")
                .photoUrls(photoUrlsCow)
                .tags(cowTagsList)
                .status(PetStatus.pending)
                .build();

        data.add(new Object[] {pet1, 200 });
        data.add(new Object[] {pet2, 200 });
        data.add(new Object[] {pet3, 200 });

        return data.iterator();
    }

    @Test(testName = "create pets",dataProvider = "getCreatePetData" )
    public void createPet(Pet pet,int code){
        String requestBody = TestHelper.getJsonString(pet);
        Response response = new PetStoreTestClass().createPet(getHostname(),requestBody);
        Assert.assertEquals(response.getStatusCode(),code);
    }

    @DataProvider
    public Iterator<Object[]> getUpdatePetData(){
        List<Object[]> data = Lists.newLinkedList();

        SortedMap<String,String> sortedMapCow = new TreeMap<String,String>();
        sortedMapCow.put("1","\"https://www.pexels.com/photo/brown-calf-inside-barn-1449656/");
        sortedMapCow.put("2","https://www.gettyimages.in/detail/photo/hereford-calf-on-white-background-looking-at-camera-royalty-free-image/155004548?adppopup=true");
        List<String>  photoUrlsCow = new ArrayList<>();
        for(Map.Entry<String,String> entry : sortedMapCow.entrySet()){
            photoUrlsCow.add(String.valueOf(entry.getValue()));
        }

        Category cowCategory = Category.builder()
                .id(30)
                .name("cowCategory_updated")
                .build();

        Tag cowTag1 =Tag.builder()
                .id(5)
                .name("cowTag1")
                .build();

        Tag cowTag2 =Tag.builder()
                .id(6)
                .name("cowTag2")
                .build();

        List<Tag> cowTagsList = new ArrayList<Tag>();
        cowTagsList.add(cowTag1);
        cowTagsList.add(cowTag2);

        Pet pet3 = Pet.builder()
                .id(300)
                .category(cowCategory)
                .name("cow_updated")
                .photoUrls(photoUrlsCow)
                .tags(cowTagsList)
                .status(PetStatus.available)
                .build();

        data.add(new Object[] {pet3, 200 });

        return data.iterator();
    }

    @Test(testName = "update pet details",dataProvider = "getUpdatePetData")
    public void updatePet(Pet pet,int code){
        String requestBody = TestHelper.getJsonString(pet);
        Response response = new PetStoreTestClass().updatePet(getHostname(),requestBody);
        Assert.assertEquals(response.getStatusCode(),code);
        updatedPetName = response.jsonPath().get("name").toString();
        updatedPetId = Long.valueOf(response.jsonPath().getInt("id"));
        updatedPetStatus = PetStatus.valueOf(response.jsonPath().get("status").toString());
    }

    @Test(testName = "get updated pet details",dependsOnMethods = "updatePet")
    public void getUpdatedPetDetails() {
        String[] statuses = {String.valueOf(PetStatus.pending),String.valueOf(PetStatus.available),String.valueOf(PetStatus.sold)};
        for (String status : statuses) {
            List<Pet> pets = new PetStoreTestClass().getPetDetails(getHostname(), status);
            for (Pet pet : pets) {
                if (pet.getName().equals(updatedPetName)) {
                    if (pet.getId() == updatedPetId) {
                        Assert.assertTrue(pet.getStatus().equals(updatedPetStatus));
                    }
                }
            }
        }
    }

}
