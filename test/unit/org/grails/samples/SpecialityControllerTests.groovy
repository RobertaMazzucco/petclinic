package org.grails.samples



import org.junit.*
import grails.test.mixin.*

@TestFor(SpecialityController)
@Mock(Speciality)
class SpecialityControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/speciality/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.specialityInstanceList.size() == 0
        assert model.specialityInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.specialityInstance != null
    }

    void testSave() {
        controller.save()

        assert model.specialityInstance != null
        assert view == '/speciality/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/speciality/show/1'
        assert controller.flash.message != null
        assert Speciality.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/speciality/list'

        populateValidParams(params)
        def speciality = new Speciality(params)

        assert speciality.save() != null

        params.id = speciality.id

        def model = controller.show()

        assert model.specialityInstance == speciality
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/speciality/list'

        populateValidParams(params)
        def speciality = new Speciality(params)

        assert speciality.save() != null

        params.id = speciality.id

        def model = controller.edit()

        assert model.specialityInstance == speciality
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/speciality/list'

        response.reset()

        populateValidParams(params)
        def speciality = new Speciality(params)

        assert speciality.save() != null

        // test invalid parameters in update
        params.id = speciality.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/speciality/edit"
        assert model.specialityInstance != null

        speciality.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/speciality/show/$speciality.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        speciality.clearErrors()

        populateValidParams(params)
        params.id = speciality.id
        params.version = -1
        controller.update()

        assert view == "/speciality/edit"
        assert model.specialityInstance != null
        assert model.specialityInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/speciality/list'

        response.reset()

        populateValidParams(params)
        def speciality = new Speciality(params)

        assert speciality.save() != null
        assert Speciality.count() == 1

        params.id = speciality.id

        controller.delete()

        assert Speciality.count() == 0
        assert Speciality.get(speciality.id) == null
        assert response.redirectedUrl == '/speciality/list'
    }
}
