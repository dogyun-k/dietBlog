let diet = {
    init: function () {
        let _this = this;

        $('.btn-read').on('click', function () {
            _this.read();
        });

        $('.btn-update-view').on('click', function () {
            _this.updateView();
        });

        $('.btn-update').on('click', function () {
            _this.update();
        });

        $('.btn-delete').on('click', function () {
            _this.delete();
        });

        $('.btn-cancel').on('click', function () {
            _this.cancel();
        });
    },
    read: function () {
        let id = $('.diet-id').val();

        $.ajax({
            method: 'GET',
            url: '/diet/' + id
        }).done(function () {
            window.location.href = this.url;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    updateView: function () {
        let id = $('.diet-id').val();

        $.ajax({
            method: 'GET',
            url: '/diet/update/' + id
        }).done(function () {
            window.location.href = this.url;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let id = $('.diet-id').val()
        let data = {
            'mealType': $('#mealType').val(),
            'content': $('#content').val()
        }

        $.ajax({
            method: 'POST',
            url: '/diet/update/' + id,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)      // Body
        }).done(function () {
            alert('수정되었습니다.');
            window.location.href = '/diet';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        let id = $('.diet-id').val();
        let data = {
            'id': id
        }

        $.ajax({
            method: 'POST',
            url: '/diet/delete',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("삭제되었습니다.");
            window.location.href = '/diet';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    cancel: function () {
        $.ajax({
            method: 'GET',
            url: '/diet'
        }).done(function () {
            window.location.href = this.url;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

diet.init();