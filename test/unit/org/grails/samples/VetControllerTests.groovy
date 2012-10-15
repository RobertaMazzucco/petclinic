package org.grails.samples



import org.junit.*
import grails.test.mixin.*

@TestFor(VetController)
@Mock(Vet)
class VetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/vet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.vetInstanceList.size() == 0
        assert model.vetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.vetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.vetInstance != null
        assert view == '/vet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/vet/show/1'
        assert controller.flash.message != null
        assert Vet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/vet/list'

        populateValidParams(params)
        def vet = new Vet(params)

        assert vet.save() != null

        params.id = vet.id

        def model = controller.show()

        assert model.vetInstance == vet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/vet/list'

        populateValidParams(params)
        def vet = new Vet(params)

        assert vet.save() != null

        params.id = vet.id

        def model = controller.edit()

        assert model.vetInstance == vet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/vet/list'

        response.reset()

        populateValidParams(params)
        def vet = new Vet(params)

        assert vet.save() != null

        // test invalid parameters in update
        params.id = vet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/vet/edit"
        assert model.vetInstance != null

        vet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/vet/show/$vet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        vet.clearErrors()

        populateValidParams(params)
        params.id = vet.id
        params.version = -1
        controller.update()

        assert view == "/vet/edit"
        assert model.vetInstance != null
        assert model.vetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/vet/list'

        response.reset()

        populateValidParams(params)
        def vet = new Vet(params)

        assert vet.save() != null
        assert Vet.count() == 1

        params.id = vet.id

        controller.delete()

        assert Vet.count() == 0
        assert Vet.get(vet.id) == null
        assert response.redirectedUrl == '/vet/list'
    }
}
