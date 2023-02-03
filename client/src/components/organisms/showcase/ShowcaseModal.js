import styled from 'styled-components';
import { useRef, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import Box from '../../atoms/Box';
import useShowcaseModal from '../../../store/showcaseModalStore';
import { UserInfoSmall } from '../../molecules/UserInfo';
import { ParagraphMedium, LabelSmall } from '../../../styles/typo';
import Category from '../../atoms/Category';
import Comments from '../comment/Comments';
import useAuthStore from '../../../store/useAuthStore';

const ShowcaseModal = ({ isModalOpen }) => {
  const modalRef = useRef(null);
  const { modalItem, toggleModalOpen, deleteShowcase } = useShowcaseModal();
  const { currentUserId } = useAuthStore(state => state);
  const { id, content, category, imageUrls, writer } = modalItem;
  const navigate = useNavigate();

  const handleClickOutside = e => {
    const condition = isModalOpen && !modalRef.current.contains(e.target);
    if (condition) {
      toggleModalOpen();
    }
  };

  const handleDeleteShowcase = async () => {
    await deleteShowcase(id);
    toggleModalOpen();
    navigate('/');
    window.location.reload();
  };

  useEffect(() => {
    const html = document.documentElement;
    html.style.overflowY = 'hidden';

    window.addEventListener('mousedown', handleClickOutside);

    return () => {
      window.removeEventListener('mousedown', handleClickOutside);
      html.style.overflowY = 'auto';
    };
  }, []);

  return (
    <Container isModalOpen={isModalOpen}>
      <Body ref={modalRef}>
        <TopContainer>
          <ImageBox width="693px" padding="0px">
            <Image src={imageUrls[0].fileURL} />
            {currentUserId === writer.id ? <EditButton onClick={handleDeleteShowcase}> X </EditButton> : null}
          </ImageBox>
          <ShowcaseContents>
            <Box>
              <UserInfoSmall id={writer.id} name={writer.nickname} image={writer.profileUrl} />
            </Box>
            <Box margin="15px 0px 35px 0px">
              <Content>{content}</Content>
            </Box>
            <Category id={category} padding="20px" color="rgba(51, 51, 51, 1)">
              {category}
            </Category>
          </ShowcaseContents>
        </TopContainer>
        <CommentListContainer>
          <Comments basePath="showcases" id={id} />
        </CommentListContainer>
      </Body>
    </Container>
  );
};

export default ShowcaseModal;

const Container = styled.div`
  display: ${props => (props.isModalOpen ? 'flex' : 'none')};
  backdrop-filter: blur(20px) brightness(150%);
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  justify-content: center;
  z-index: 102;
  background-color: rgba(0, 0, 0, 0.5);
  overflow: auto;
`;

const Body = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 100px;
  width: 1057px;
  height: 1000px;
  z-index: 103;
  gap: 32px;
`;

const TopContainer = styled.div`
  display: flex;
  height: 632px;
  gap: 32px;
`;

const ImageBox = styled(Box)`
  position: relative;
  margin-right: 32px;
`;

const Image = styled.img`
  object-fit: contain;
  background-color: black;
  height: 100%;
`;

const EditButton = styled.button`
  position: absolute;
  ${LabelSmall};
  color: white;
  background-color: var(--gray-600);
  border: 2px solid var(--gray-900);
  &.focus {
    border: 2px solid var(--gray-700);
    background-color: var(--gray-700);
    color: white;
    box-shadow: var(--boxShadow-00) var(--gray-700);

    &:hover {
      background-color: var(--gray-500);
    }
  }

  &:hover {
    background-color: var(--gray-500);
  }
`;

const Content = styled.div`
  ${ParagraphMedium};
  height: 438px;
`;

const ShowcaseContents = styled.div`
  width: 331px;
  display: flex;
  flex-direction: column;
`;

const CommentListContainer = styled.div`
  width: 100%;
  background-color: white;
`;
