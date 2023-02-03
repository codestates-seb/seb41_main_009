import styled from 'styled-components';
import axios from 'axios';
import { BlackButton, WhiteButton } from '../../atoms/Buttons';
import { LabelListTitle } from '../../../styles/typo';
import usePostStore from '../../../store/postStore';
import useGetSeriesListbyUser from '../../../hooks/useGetSeriesListbyUser';
import useAuthStore from '../../../store/useAuthStore';

const Container = styled.div`
  box-sizing: border-box;

  /* Auto layout */

  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-end;
  padding: 20px 40px;
  width: 100%;
  height: ${props => (props.height ? props.height : '500px')};
  gap: 20px;

  background: ${props => props.background || '#ffffff'};
  border: 1px solid #000000;
  box-shadow: 8px 8px 0px #000000;
  overflow: hidden;
`;

const ComponentLabel = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 10px;
  gap: 10px;

  width: 100%;
  height: fit-content;
  ${LabelListTitle}
`;

const ButtonArea = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: flex-end;
  padding: 0px;
  gap: 20px;
`;

const DropdownListContainer = styled.ul`
  width: 100%;
  display: flex;
  flex-direction: column;
  top: 70px;
  left: 0px;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  background-color: white;
  border: 1px solid black;
`;

const DropdownItem = styled.li`
  color: black;
  width: 100%;
  height: fit-content;
  padding: 10px;
  cursor: pointer;
  border-bottom: 1px solid var(--gray-200);
  background-color: ${props => (props.selected ? 'var(--gray-200)' : '')};
`;

// writer의 정보를 못가져옴
const DropdownSeriesList = ({ post }) => {
  const { currentSeriesId, setCurrentSeriesId, setIsOpen } = usePostStore();
  const { currentUserId } = useAuthStore(state => state);

  // 로그인한 사용자의 id를 가져오도록 수정이 되어야함
  const { seriesList } = useGetSeriesListbyUser(currentUserId);

  console.log(post, 'post in DropdownSeriesList');
  const toggleOpen = () => {
    setIsOpen();
  };

  const submitChange = () => {
    setIsOpen();
    const body = {
      title: post.title,
      content: post.content,
      category: post.category,
      description: 'description',
      seriesId: currentSeriesId,
    };

    console.log(body, 'body');

    // if (post.imgUrls) body.imgUrls = post.imgUrls;
    axios
      .patch(`posts/${post.id}`, body, { withCredentials: true })
      .then(res => {
        console.log(res);
      })
      .catch(err => {
        // dialog를 나타나게 하고 싶음
        alert(err.message);
      });
  };

  return (
    <Container>
      <ComponentLabel>Add to My Series</ComponentLabel>
      <DropdownListContainer>
        {seriesList?.map(series =>
          currentSeriesId === series.id ? (
            <DropdownItem
              key={series.id}
              seriesId={series.id}
              selected
              onClick={() => {
                setCurrentSeriesId(series.id);
              }}>
              {series.title}
            </DropdownItem>
          ) : (
            <DropdownItem
              key={series.id}
              seriesId={series.id}
              onClick={() => {
                setCurrentSeriesId(series.id);
              }}>
              {series.title}
            </DropdownItem>
          ),
        )}
      </DropdownListContainer>

      <ButtonArea>
        <WhiteButton onClick={toggleOpen} width="fit-content">
          Cancel
        </WhiteButton>
        <BlackButton onClick={submitChange} width="fit-content">
          OK
        </BlackButton>
      </ButtonArea>
    </Container>
  );
};

export default DropdownSeriesList;
