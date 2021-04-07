import pytest
values = [(True) for x in range(20)]

@pytest.mark.parametrize("test_truth", values)
def test_all_true(test_truth):
    assert test_truth

def test_death():
    assert False, "kaboom"
